package cafe.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import cafe.mvc.exception.AddException;
import cafe.mvc.exception.DuplicatedException;
import cafe.mvc.exception.ModifyException;
import cafe.mvc.exception.NotFoundException;
import cafe.mvc.model.dto.UsersDTO;

public interface UsersService {
	/**
	 * 회원가입: user 테이블 insert
	 * @return 
	 * */
	void userInsert(UsersDTO usersDTO) throws SQLException, AddException, DuplicatedException;
	
	/**
	 * 회원 정보 수정: user 테이블 update(전화번호/이름/적립금...?)
	 * */
	void userPwdUpdate(UsersDTO usersDTO) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * 로그인
	 * */
	UsersDTO login(String userTel, int userPwd) throws SQLException, NotFoundException;
	
	/**
	 * 적립금 확인: user 테이블 select
	 * */
	UsersDTO userPointCh(String userTel) throws SQLException, ModifyException, NotFoundException;
	
	/**
	 * 전체 유저 검색
	 * */
	List<UsersDTO> userSelectAll() throws SQLException, NotFoundException;
	
	/**
	 * 전화번호로 유저 검색
	 * */
	UsersDTO selectByUserTel(String userTel) throws SQLException, NotFoundException;
	
	/**
	 * 더 필요한 메소드 있을까요?
	 * */
}
