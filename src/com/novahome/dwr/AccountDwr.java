package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Account;
import com.novahome.data.service.AccountService;

@Scope("prototype")
@Component("account_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "account_dwr"), name = "account_dwr")
public class AccountDwr {
	@Resource
	private AccountService accountService;
	
	@RemoteMethod
	public String saveAccount(Account account) {
		return accountService.saveAccount(account);
	}
	
	@RemoteMethod
	public String login(String username,String password) {
		return accountService.login(username, password);
	}
	
	@RemoteMethod
	public boolean logout() {
		return accountService.logout();
	}
	
	@RemoteMethod
	public String cookielogin(String username,String value) {
		return accountService.cookielogin(username, value);
	}
}
