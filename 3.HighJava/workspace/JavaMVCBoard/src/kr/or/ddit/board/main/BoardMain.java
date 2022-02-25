package kr.or.ddit.board.main;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * 게시판을 관리하는 다음 기능들을 구현하시오.
 * 
 * 기능 : 전체목록 출력, 새글작성, 수정, 삭제, 검색 
 * 
 * 시퀀스의 다음값 구하기
 * 시퀀스이름.nextVal
 */
public class BoardMain {
	private Scanner sc;
	SimpleDateFormat sdf;
	private IBoardService boardService;
	private static int no;
	
	public BoardMain() {
		sc = new Scanner(System.in);
		sdf = new SimpleDateFormat("yy/MM/dd");
		boardService = BoardServiceImpl.getInstance();
	}
	
	/*
	 * 시작
	 */
	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();
	}

	/*
	 * 메뉴 출력
	 */
	private void displayMenu() {
		System.out.println("====================================================");
		System.out.println("1.게시글 작성\t2.게시글 읽기\t3.게시글 검색\t0.작업 종료");
		System.out.println("====================================================");
		System.out.print("원하는 작업 선택 >");
	}
	
	/*
	 * 메인
	 */
	private void start() {
		while(true) {
			displayList();	//목록 출력
			displayMenu();	//메뉴 출력
			
			int choice = Integer.parseInt(sc.nextLine());	//작업 선택
			switch(choice) {
			case 1:	//게시글 작성
				write();
				break;
			case 2:	//게시글 읽기
				read();
				break;
			case 3:	//게시글 검색
				search();
				break;
			case 0:	//작업 종료
				System.out.println("작업을 종료합니다.");
				System.exit(3);
			default :
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}

	/*
	 * 목록 
	 */
	private void displayList() {
		System.out.println();
		System.out.println("====================================================");
		System.out.println("=========== 대          덕          게          시          판 ============");
		System.out.println("====================================================");
		System.out.println(" 번호\t제목\t\t작성자\t작성일");
		System.out.println("====================================================");
		
		List<BoardVO> boardList = boardService.listBoard();
		
		for(BoardVO bv : boardList) {
			System.out.println(bv.getBoardNo() + "\t"
							 + bv.getBoardTitle() + "\t\t"
							 + bv.getBoardWriter() + "\t"
							 + sdf.format(bv.getBoardDate()));
			System.out.println("----------------------------------------------------");
		}	
		System.out.println("====================================================");
		System.out.println("출력작업 완료...");
	}
	
	/*
	 * 번호 체크
	 */
	private boolean chkNumber(int boardNo) {
		boolean chk = false;
		
		chk = boardService.chkNumber(boardNo);
		
		return chk;
	}
	
	/*
	 * 게시글 삭제
	 */
	private void delete() {
		System.out.println();
		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("1.예\t2.아니오");
		System.out.print("원하는 작업 선택 >");
		int no2 = Integer.parseInt(sc.nextLine());
		switch(no2) {
		case 1:	//게시물 삭제
			int cnt = boardService.deleteBoard(no);
			
			if(cnt > 0) {
				System.out.println("게시글 삭제 완료");
			}else {
				System.out.println("게시글 삭제 실패");
			}
			break;
		case 2:	//뒤로 가기
			break;
		default :
			System.out.println("번호를 잘못 입력했습니다.");
			System.out.println("다시 입력하세요.");	
		}
	}
	
	/*
	 * 게시글 수정
	 */
	private void update() {
		System.out.println();
		System.out.print("제목 >");
		String boardTitle = sc.nextLine();
		System.out.print("내용 >");
		String boardContent = sc.nextLine();
	
		BoardVO bv = new BoardVO();
		bv.setBoardNo(no);
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		
		int cnt = boardService.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println("게시글 수정 완료");
		}else {
			System.out.println("게시글 수정 실패");
		}
	}
	
	/*
	 * 게시글 작성
	 */
	private void write() {
		System.out.println();
		System.out.print("제목>");
		String boardTitle = sc.nextLine();
		System.out.print("내용>");
		String boardContent = sc.nextLine();
		System.out.print("작성자>");
		String boardWriter = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardWriter(boardWriter);
		
		int cnt = boardService.writeBoard(bv);
		
		if(cnt > 0) {
			System.out.println("게시글 작성 성공");
		}else {
			System.out.println("게시글 작성 실패");
		}
	}

	/*
	 * 게시글 검색
	 */
	private void search() {
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.print("게시글 제목 >");
		String boardTitle = sc.nextLine();
		System.out.print("게시글 내용 >");
		String boardContent = sc.nextLine();
		System.out.print("게시글 작성자 >");
		String boardWriter = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardWriter(boardWriter);
		
		List<BoardVO> boardList = boardService.searchBoard(bv);
		
		loop : while(true) {
			if(boardList.size() > 0) {
				System.out.println();
				System.out.println("====================================================");
				System.out.println("=========== 검          색          게          시          판 ============");
				System.out.println("====================================================");
				System.out.println(" 번호\t제목\t\t작성자\t작성일");
				System.out.println("====================================================");
				
				for(BoardVO bv2 : boardList) {
					System.out.println(bv2.getBoardNo() + "\t"
									 + bv2.getBoardTitle() + "\t\t"
									 + bv2.getBoardWriter() + "\t"
									 + sdf.format(bv2.getBoardDate()));
					System.out.println("----------------------------------------------------");
				}
			}else {
				System.out.println("====================================================");
				System.out.println("검색된 결과가 존재하지 않습니다.");
				break;
			}
			System.out.println("====================================================");
			System.out.println("검색작업 완료...");
			System.out.println("====================================================");
			System.out.println("1.게시글 읽기\t0.뒤로 가기");
			System.out.println("====================================================");
			System.out.print("원하는 작업 선택 >");
			int choice = Integer.parseInt(sc.nextLine());	//작업 선택
			switch(choice) {
			case 1:	//게시글 읽기
				read();
				break;
			case 0:	//목록으로
				break loop;
			default :
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}

	/*
	 * 게시글 읽기
	 */
	private void read() {
		boolean chk = false;
		int count = 0;
		do {
			System.out.println();
			System.out.println("게시글 번호를 입력하세요.");
			System.out.print("번호 >");
			no = Integer.parseInt(sc.nextLine());
			
			chk = chkNumber(no);
			
			if(chk == false) {
				count++;
				System.out.println(no + "번 게시물은 존재하지 않습니다.");
				System.out.println("다른 번호를 입력해주세요.");
			}
			
			if(count == 5) {
				System.out.println("찾으시는 게시물은 존재하지 않습니다.");
				System.out.println("목록으로 돌아갑니다.");
				return;
			}
		}while(chk == false);
		
		List<BoardVO> boardList = boardService.readBoard(no);
		
		int boardNo = 0;
		String boardTitle = null;
		String boardContent = null;
		String boardWriter = null;
		Object boardDate = null;
		
		for(BoardVO bv : boardList) {
			boardNo = bv.getBoardNo();
			boardTitle = bv.getBoardTitle();
			boardContent = bv.getBoardContent();
			boardWriter = bv.getBoardWriter();
			boardDate = bv.getBoardDate();
			System.out.println(boardContent);
		}
		
		System.out.println();
		System.out.println("====================================================");
		System.out.println(boardNo + "번 게시물");
		System.out.println("제목 : " + boardTitle);
		System.out.println("====================================================");
		System.out.println("내용 : ");
		System.out.println(boardContent);
		System.out.println();
		System.out.println("====================================================");
		System.out.println("작성자 : " + boardWriter);
		System.out.println("작성일 : " + boardDate);
		System.out.println("====================================================");
		System.out.println("1.게시글 수정\t2.게시글 삭제\t0.뒤로 가기");
		System.out.println("====================================================");
		System.out.print("원하는 작업 선택 >");
		int choice = Integer.parseInt(sc.nextLine());	//작업 선택
		switch(choice) {
		case 1:	//게시글 수정
			update();
			break;
		case 2:	//게시글 삭제
			delete();
			break;
		case 0:	//뒤로 가기
			break;
		default :
			System.out.println("번호를 잘못 입력했습니다.");
			System.out.println("다시 입력하세요.");	
		}
	}
}
