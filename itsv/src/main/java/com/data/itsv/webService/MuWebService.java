package com.data.itsv.webService;

import javax.jws.WebParam;
import javax.jws.WebService;
@WebService(name ="service" ,targetNamespace = "http://webService.big.data.com/")
public interface MuWebService {
    String getHcsj(@WebParam(name = "qsrq", targetNamespace = "http://webService.big.data.com/") String qsrq,
                   @WebParam(name = "jsrq", targetNamespace = "http://webService.big.data.com/") String jsrq,
                   @WebParam(name = "xm", targetNamespace = "http://webService.big.data.com/") String xm);

    String getWxjh(String userName, String userId, String str) ;

    // User getUser(String userName, String userId) ;
}
