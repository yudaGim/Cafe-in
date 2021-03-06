package cafe.mvc.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cafe.mvc.controller.CartController;
import cafe.mvc.controller.OrdersController;
import cafe.mvc.controller.UsersController;
import cafe.mvc.model.dto.OrderLineDTO;
import cafe.mvc.model.dto.OrdersDTO;
import cafe.mvc.model.dto.ProductDTO;
import cafe.mvc.session.Session;
import cafe.mvc.controller.ProductController;
import cafe.mvc.model.dto.StockDTO;
import cafe.mvc.model.dto.UsersDTO;
import cafe.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	static String guestId = "guest";
	
	/**
	 * 메인 메뉴
	 * */
	public static void mainMenu() {
		while(true) {

			try {
				System.out.println("\n" + "[ 1. 회원가입  |  2. 로그인  |  3. 비회원주문  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu = Integer.parseInt(sc.nextLine());
				switch(menu) {
				case 1 :
					MenuView.userInsert();
					break;
				case 2 :
					// 관리자 아이디로 로그인 할 경우 관리자 메뉴로 이동함
					MenuView.login();
					break;
				case 3 : 
					MenuView.guestLogin();
					break;
				case 0 : 
					MenuView.exit();
				default:
					System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");

			}
		}
	}

	
	/**
	 * 로그인 유저 메뉴
	 * */
	public static void userMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n[ 1. 주문하기  |  2. 지난 주문 내역  |  3. 적립금 확인  |  4. 비밀번호 변경  |  9. 로그아웃  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu =Integer.parseInt( sc.nextLine());
				switch(menu) {
					case 1 :
						MenuView.orderMenu(userTel);
						break;
					case 2 :
						OrdersController.selectByUserTel(userTel);
						break;
					case 3 :
						UsersController.userPointCh(userTel);
						break;
					case 4 :
						MenuView.userPwdUpdate(userTel);
						break;
					case 9 :
						MenuView.logout(userTel);
						return;
					case 0 :
						MenuView.exit();
					default:
						System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
	
	/**
	 * 주문 메뉴
	 * */
	public static void orderMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n" + "[ 1. 커피 메뉴 보기  |  2. 스무디 메뉴 보기  |  3. 차 메뉴 보기  |  4. 디저트 메뉴 보기  |  5. 장바구니 보기  |  9. 뒤로 가기  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu =Integer.parseInt( sc.nextLine());
				switch(menu) {
					case 1 :
						ProductController.selectByGroup("C");
						MenuView.categoryMenu(userTel);
						break;
					case 2 :
						ProductController.selectByGroup("S");
						MenuView.categoryMenu(userTel);
						break;
					case 3 :
						ProductController.selectByGroup("T");
						MenuView.categoryMenu(userTel);
						break;
					case 4 :
						ProductController.selectByGroup("D");
						MenuView.categoryMenu(userTel);
						break;
					case 5 :
						MenuView.cartMenu(userTel);
						break;
					case 9 :
						if(userTel.equals(guestId)) {
							MenuView.logout(userTel);
						}
						return;
					case 0 :
						MenuView.exit();
					default:
						System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
	
	/**
	 * 카테고리별 상품 페이지
	 * */
	public static void categoryMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n" + "[ 1. 장바구니에 상품 담기  |  9. 뒤로 가기  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu =Integer.parseInt(sc.nextLine());
				switch(menu) {
					case 1 :
						MenuView.putCart(userTel);
						return;
					case 9 :
						return;
					case 0 :
						MenuView.exit();
					default:
						System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
    
    /**
     * 관리자 메뉴
     * */
	public static void adminMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n" + "[ 관리자 메뉴 ]");
				System.out.println("[ 1. 상품 관리  |  2. 회원 관리  |  3. 주문 관리  |  9. 로그아웃  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu = Integer.parseInt(sc.nextLine());
				switch(menu) {
				case 1 :
					MenuView.prodManageMenu(userTel);
					break;
				case 2 :
					MenuView.usersManageMenu(userTel);
					break;
				case 3 :
					MenuView.ordersManageMenu(userTel);
					break;
				case 9 : 
					MenuView.logout(userTel);
					return;
				case 0 : 
					MenuView.exit();
				default:
					System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
	
	/**
	 * 상품 관리 메뉴
	 * */
	public static void prodManageMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n" + "[ 1. 상품 조회  |  2. 상품 등록  |  3. 상품 수정  | 4. 상품 상태 변경  |  5. 상품 재고 변경  |  9. 뒤로 가기  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu = Integer.parseInt(sc.nextLine());
				switch(menu) {
				case 1 :
					ProductController.productSelectAll();
					break;
				case 2 :
					MenuView.productInsert();
					break;
				case 3 :
					MenuView.productUpdate();
					break;
				case 4 :
					MenuView.productStateUpdate();
					break;
				case 5 :
					MenuView.dessertStockUpdate();
					break;
				case 9 : 
					return;
				case 0 : 
					MenuView.exit();
				default:
					System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
	
	/**
	 * 회원 관리 메뉴
	 * */
	public static void usersManageMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n" + "[ 1. 전체 회원 조회  |  2. 회원 정보 검색  |  9. 뒤로 가기  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu = Integer.parseInt(sc.nextLine());
				switch(menu) {
				case 1 :
					UsersController.userSelectAll();
					break;
				case 2 :
					MenuView.userSelectByUserTel();
					break;
				case 9 : 
					return;
				case 0 : 
					MenuView.exit();
				default:
					System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
	
	/**
	 * 주문 관리 메뉴
	 * */
	public static void ordersManageMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n" + "[ 1. 현재 주문 조회  |  2. 주문 상태 변경  |  3. 일간 매출 통계  |  4. 제품별 판매 통계  |  9. 뒤로 가기  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu = Integer.parseInt(sc.nextLine());
				switch(menu) {
				case 1 :
					OrdersController.selectOngoingOrder();
					break;
				case 2 :
					MenuView.orderStateUpdate();
					break;
				case 3 :
					OrdersController.dailySalesStatistic(new SimpleDateFormat("yyMMdd").format(new Date()));
					break;
				case 4 :
					OrdersController.productSalesStatistic();
					break;
				case 9 : 
					return;
				case 0 : 
					MenuView.exit();
				default:
					System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
    
    /**
     * 장바구니 메뉴
     * */
	public static void cartMenu(String userTel) {
		while(true) {
			try {
				System.out.println("\n" + "[ 1. 결제  |  2. 장바구니 조회  |  3. 장바구니 상품 삭제  |  4. 장바구니 전체 삭제  |  9. 뒤로 가기  |  0. 종료 ]");
				System.out.print("▶ ");
				int menu = Integer.parseInt(sc.nextLine());
				switch(menu) {
				case 1 :
					MenuView.orderInsert(userTel);
					return;
				case 2 :
					CartController.viewCart(userTel);
					break;
				case 3 :
					MenuView.deleteCartByCode(userTel);
					break;
				case 4 :
					CartController.deleteCartAll(userTel);
					SuccessView.printMessage("장바구니에 담긴 상품을 모두 삭제했습니다.");
					break;
				case 9 : 
					return;
				case 0 :
					MenuView.exit();
				default:
					System.out.println("메뉴를 잘못 선택하셨습니다.");
				}
			} catch (Exception e) {
				FailView.errorMessage("오류가 발생했습니다.\n다시 한 번 시도해주세요.");
			}
		}
	}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 로그인 메소드
	 * */
	public static void login() throws Exception{
		System.out.println("전화번호를 입력해주세요.");
		System.out.print("▶ ");
		String userTel = sc.nextLine();
		
		System.out.println("비밀번호를 입력해주세요.");
		System.out.print("▶ ");
		String stringPwd = sc.nextLine();
		int userPwd = 0;
		
		if(stringPwd.matches("[+-]?\\d*(\\.\\d+)?")) {
			userPwd = Integer.parseInt(stringPwd);
		} else {
			System.out.println("비밀번호는 숫자만 입력해주세요.");
			return;
		}
		
		UsersController.login(userTel, userPwd);
	}
	
	/**
	 * 비회원 임시 로그인 메소드
	 * */
	public static void guestLogin(){
		Session session = new Session(guestId);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		MenuView.orderMenu(guestId);
	}

	/**
	 * 로그아웃 메소드
	 * */
	public static void logout(String userTel) {
		Session session = new Session(userTel);
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
		MenuView.mainMenu();
	}
	
	/**
	 * 회원가입 메소드
	 */
	public static void userInsert() throws Exception {
			System.out.print("전화번호 ex)010-1111-1111 ▶ ");
			String userTel = sc.nextLine();
			if(userTel.matches("^010-(?:\\d{4})-\\d{4}$")) {
				System.out.print("이름 ▶ ");
				String userName = sc.nextLine();
				
				System.out.print("비밀번호 ▶ ");
				int userPwd = Integer.parseInt(sc.nextLine());	
				
				UsersDTO usersDTO = new UsersDTO(userTel, userName, userPwd);
				UsersController.userInsert(usersDTO);
			}else {
				System.out.println("전화번호를 확인해주세요.");
				MenuView.mainMenu();
			}
			
	
	}
	
	/**
	 * 장바구니 추가 메소드
	 * */
	public static void putCart(String userTel) throws Exception {
		System.out.println("장바구니에 추가할 상품 코드를 입력해주세요.");
		System.out.print("▶ ");
		String prodCode = sc.nextLine();
		
		System.out.println("원하시는 상품 수량을 입력해주세요.");
		System.out.print("▶ ");
		int qty = Integer.parseInt(sc.nextLine());
		
		CartController.putCart(userTel, prodCode, qty);
	}
	
	/**
	 * 장바구니 부분 삭제 메소드
	 * */
	public static void deleteCartByCode(String userTel) throws Exception {
		System.out.println("장바구니에서 제외할 상품 코드를 입력해주세요.");
		System.out.print("▶ ");
		String prodCode = sc.nextLine();
		
		CartController.deleteCartByCode(userTel, prodCode);
	}
	
	/**
	 * 결제 메소드
	 * */
	public static void orderInsert(String userTel) throws Exception {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(userTel);
		
		Map<ProductDTO, Integer> cart = (Map<ProductDTO, Integer>) session.getAttributes("cart");
		
		if(cart == null || cart.isEmpty()) {
			System.out.println("장바구니에 담긴 상품이 없어 결제할 수 없습니다.");
			return;
		}
		
		int payPoint = 0;
		
		if(!userTel.equals(guestId)) {
			System.out.println("상품을 결제하겠습니다.");
			System.out.println("사용하실 적립금 액수를 입력해주세요.");
			System.out.print("▶ ");
			payPoint = Integer.parseInt(sc.nextLine());
		}
		
		System.out.println("결제 방법을 선택해주세요.");
		System.out.println("[ 카드 / 현금 ]");
		System.out.print("▶ ");
		String payMethod = sc.nextLine();
		
		System.out.println("포장 여부를 선택해주세요.");
		System.out.println("[ 0. 매장에서 먹고 갈게요.  |  1. 포장해 주세요. ]");
		System.out.print("▶ ");
		int takeout = Integer.parseInt(sc.nextLine());
		
		List<OrderLineDTO> list = new ArrayList<OrderLineDTO>();
		
		for(ProductDTO productDTO : cart.keySet()) {
			list.add(new OrderLineDTO(0, 0, productDTO.getProdCode(), cart.get(productDTO), 0));

		}
		
		OrdersDTO ordersDTO = new OrdersDTO(0, userTel, 0, payMethod, payPoint, 0, null, takeout);
		ordersDTO.setOrderLineList(list);
		
		OrdersController.orderInsert(ordersDTO);

	}
	
	/**
	 * 상품 등록 메소드
	 * */
	public static void productInsert() throws Exception {
		System.out.println("상품을 등록해주세요.");
		 System.out.print("상품코드 ▶ ");
		 String prodCode = sc.nextLine();
		 //잘맞게 들어왔는지 체크 '알파벳 숫자 숫자'
		 if (prodCode.matches("^[C][0-9][0-9]*$")|| prodCode.matches("^[T][0-9][0-9]*$") || prodCode.matches("^[S][0-9][0-9]*$") | prodCode.matches("^[D][0-9][0-9]*$")){
			 System.out.print("상품이름 ▶ ");
			 String prodName = sc.nextLine();
			 
			 System.out.print("상품가격 ▶ ");
			 int prodPrice = Integer.parseInt(sc.nextLine());
			 
			 System.out.print("상품소개 ▶ ");
			 String prodDetail = sc.nextLine();
			 
			 System.out.print("상품상태 ▶ ");
			 int prodState = Integer.parseInt(sc.nextLine());

			 ProductDTO product = new ProductDTO(prodCode, null, prodName, prodPrice, prodDetail, prodState);
			 
			 if(prodCode.substring(0, 1).equals("D")) { 
				 System.out.print("디저트 재고량 ▶ ");
				 int prodStock = Integer.parseInt(sc.nextLine());
				 StockDTO stock = new StockDTO(prodCode, prodStock);
				 product.setStock(stock);

			 }

			 ProductController.productInsert(product);
		 }else {
			 System.out.println("상품코드는 알파벳1개(C,T,S,D 중) 숫자2개로 입력해주세요.");
			 return;
		 }
	}
	
	/**
	 * 상품 수정 메소드
	 * */
	public static void productUpdate() throws Exception {
		System.out.println("상품을 수정해주세요.");
		 System.out.print("상품코드 ▶ ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("상품가격 ▶ ");
		 int prodPrice = Integer.parseInt(sc.nextLine());
		 
		 System.out.print("상품소개 ▶ ");
		 String prodDetail = sc.nextLine();
	
		 ProductController.productUpdate(new ProductDTO(prodCode, null, null, prodPrice, prodDetail, 0));
	}
	
	/**
	 * 상품 상태 변경 메소드
	 * */
	public static void productStateUpdate() throws Exception {
		 System.out.println("상품 상태를 수정해주세요.");
		 System.out.print("상품코드 ▶ ");
		 String prodCode = sc.nextLine();
		 
		 System.out.print("상품상태 ▶ ");
		 int prodState = Integer.parseInt(sc.nextLine());
		 ProductController.productStateUpdate(prodCode, prodState);
	}
	
	/**
	 * 디저트 재고 변경 메소드
	 * */
	public static void dessertStockUpdate() {
		 System.out.println("디저트 재고 수량을 수정해주세요.");
		 System.out.print("상품코드 (D+숫자)▶ ");
		 String prodCode = sc.nextLine();
		 if (prodCode.matches("^[D][0-9][0-9]*$")){
			 System.out.print("디저트 재고 수량 ▶ ");
			 int prodStock = Integer.parseInt(sc.nextLine());
			 ProductDTO productDTO = new ProductDTO(prodCode, null, null, 0, null, prodStock);
			 StockDTO stockDTO = new StockDTO(prodCode, prodStock);
			 productDTO.setStock(stockDTO);
			 ProductController.dessertStockUpdate(stockDTO);
		 } else {
				 System.out.println("디저트의 상품코드는 ex)D01 형식입니다.");
		         dessertStockUpdate();
		 }
	}
	
	/**
	 * 회원 비밀번호 수정 메소드
	 * */
	public static void userPwdUpdate(String userTel) throws Exception {
		System.out.println("변경할 비밀번호를 입력해주세요.");
		System.out.print("▶ ");
		int userPwd = Integer.parseInt(sc.nextLine());
		
		UsersDTO usersDTO = new UsersDTO(userTel, null, userPwd);
		UsersController.userPwdUpdate(usersDTO);
	}
	
	/**
	 * 주문 상태 변경 메소드
	 * */
	public static void orderStateUpdate() {
		System.out.println("변경할 주문번호");
		System.out.println("▶ ");
		int orderNum = Integer.parseInt(sc.nextLine());
		
		System.out.println("변경할 주문 상태 코드");
		System.out.println("[ 0 : 접수대기 | 1 : 주문접수 | 2 : 상품 준비중 | 3 : 상품 준비 완료 | 4 : 픽업완료 | 5 : 주문취소 ]");
		System.out.print("▶ ");
		int stateCode = Integer.parseInt(sc.nextLine());
		OrdersController.orderStateUpdate(new OrdersDTO(orderNum, stateCode));
	}
	
	/**
	 * 회원 정보 검색
	 * */
	public static void userSelectByUserTel() throws Exception {
		System.out.println("검색할 회원의 전화번호를 입력해주세요.");
		System.out.print("▶ ");
		String userTel = sc.nextLine();
		
		UsersController.selectByUserTel(userTel);
	}
	
	/**
	 * 종료 메소드
	 * */
	public static void exit() {
		System.out.println("다음에 또 이용해주세요.");
		System.exit(0);
	}
}
