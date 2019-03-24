package com.ddct.acmecars.error;

public class ErrorResponse
{
    private String errMsg;
    
    public ErrorResponse( String errMsg )
    {
    	this.errMsg = errMsg;
    }
    
    public String getErrMsg()
    {
    	return this.errMsg;
    }
}
