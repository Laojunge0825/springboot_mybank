package com.yc.tx.controller;

import com.yc.tx.bean.Accounts;
import com.yc.tx.service.AccountService;
import com.yc.tx.vo.AccountVo;
import com.yc.tx.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: mybank
 * @author: 作者
 * @create: 2021-04-24 20:52
 */
@Controller
@Slf4j  //lambok
@Api(value = "银行账户操作接口",tags = {"账户操作接口","控制层"})
public class AccountController {
    @Autowired
    private AccountService accountService;


    @RequestMapping (value="/openAccounts",method = RequestMethod.GET)
    @ApiOperation(value = "开户", notes = "根据金额完成开户操作，注意此时的金额表示开户时存的金额")
    @ApiImplicitParam(name = "money", value = "开户金额", required = true, dataType = "double")


    public  @ResponseBody ResultVO openAccount(AccountVo accountVo){
        log.warn("用户请求开户，存入"+accountVo.getMoney());
        ResultVO rv=new ResultVO();
        try {
            Accounts a = new Accounts();
            double money = 1;
            if (accountVo.getMoney() != null && accountVo.getMoney() > 0) {
                money = accountVo.getMoney();
            }
            Integer id = accountService.openAccount(a, money);
            a.setAccountId(id);
            a.setBalance(money);
            rv.setCode(1);
            rv.setData(a);
        }catch (Exception ex){
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }

        return rv;
    }

    @RequestMapping (value="/deposite",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "存款", notes = "根据账户编号及金额完成存款操作，注意此时的金额表示要存的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "int"),
            @ApiImplicitParam(name = "money", value = "存款金额", required = true, dataType = "double")
    })

    public @ResponseBody ResultVO Deposite(AccountVo accountVo){

        log.warn("用户"+accountVo.getAccountId()+"，存入"+accountVo.getMoney());
        ResultVO rv=new ResultVO();
        try {
            Accounts account = new Accounts();
            account.setAccountId(accountVo.getAccountId());
            Accounts a = accountService.deposite(account, accountVo.getMoney(), "deposite", null);
            a.setAccountId(accountVo.getAccountId());
            rv.setCode(1);
            rv.setData(a);
        }catch (Exception ex){
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }
        return  rv;
    }
    @RequestMapping (value="/withdraw",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "取款", notes = "根据账户编号及金额完成取款操作，注意此时的金额表示要取的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "int"),
            @ApiImplicitParam(name = "money", value = "取款金额", required = true, dataType = "double")
            })

    public @ResponseBody ResultVO Withdraw(AccountVo accountVo){
        log.warn("用户"+accountVo.getAccountId()+"，取出"+accountVo.getMoney());
        ResultVO rv=new ResultVO();
        try {
            Accounts account = new Accounts();
            account.setAccountId(accountVo.getAccountId());
            Accounts a = accountService.withdraw(account, accountVo.getMoney(), "withdraw", null);
            rv.setCode(1);
            rv.setData(a);
        }catch (Exception ex){
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg("用户余额不足");
        }
        return  rv;
    }
    @RequestMapping (value="/transfer",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "转账", notes = "根据账户编号及金额, 对方接收账号来完成转账操作，注意此时的金额表示要转的金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountId", value = "自己账户编号", required = true, dataType = "int"),
            @ApiImplicitParam(name = "money", value = "转账金额", required = true, dataType = "double"),
            @ApiImplicitParam(name = "inAccountId", value = "对方接收账号", required = true, dataType = "int")})

    public @ResponseBody ResultVO Transfer(AccountVo accountVo){
        log.warn("用户"+accountVo.getAccountId()+"，转账"+accountVo.getMoney()+"给用户"+accountVo.getInAccountId());
        ResultVO rv=new ResultVO();
        try {
            Accounts ina=new Accounts();
            ina.setAccountId(accountVo.getInAccountId());
            Accounts out =new Accounts();
            out.setAccountId(accountVo.getAccountId());
            Accounts a = accountService.transfer(ina,out,accountVo.getMoney());
            rv.setCode(1);
            rv.setData(a);
        }catch (Exception ex){
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg("用户余额不足");
        }
        return  rv;
    }
    @RequestMapping(value = "/findAll" ,method = RequestMethod.GET)
    public @ResponseBody
    List<Accounts> FindAll(){
        List<Accounts>list=accountService.findAll();
        return list;
    }
    @RequestMapping(value = "/findbyId" ,method = RequestMethod.GET)
    public @ResponseBody
    ResultVO FindbyId(AccountVo accountVo){
        ResultVO rv=new ResultVO();
        Accounts account=new Accounts();
        account.setAccountId(accountVo.getAccountId());
        Accounts a=accountService.showBalance(account);
        rv.setData(a);
        return rv;
    }
}
