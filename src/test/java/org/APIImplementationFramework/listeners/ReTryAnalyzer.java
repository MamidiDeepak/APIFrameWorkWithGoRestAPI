package org.APIImplementationFramework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReTryAnalyzer implements IRetryAnalyzer {

    int reTry = 0;
    int maxReTry = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(reTry<maxReTry){
            reTry++;
            return true;
        }
        return false;
    }
}
