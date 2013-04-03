package com.djungowski.listeners;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.AutomaticBean;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;

public class NyanCatListener
    extends AutomaticBean
    implements AuditListener
{
    private PrintWriter mWriter = new PrintWriter(System.out);
    private boolean auditFinished = false;
    private int mTotalErrors;
    private int mErrors;

    //private final String ESC = \x1b[;
    private final String ESC = "\u001b[";
    private int catCalls = 0;

    public void auditStarted(AuditEvent aEvt)
    {
        mTotalErrors = 0;
        this.printCat();
    }

    public void auditFinished(AuditEvent aEvt)
    {
        this.auditFinished = true;
        this.printCat();
    }

    public void fileStarted(AuditEvent aEvt)
    {
        this.printCat();
    }

    public void fileFinished(AuditEvent aEvt)
    {
        this.printCat();
    }

    public void addError(AuditEvent aEvt)
    {
        this.printCat();
        if (SeverityLevel.ERROR.equals(aEvt.getSeverityLevel())) {
            mErrors++;
            mTotalErrors++;
        }
    }

    public void addException(AuditEvent aEvt, Throwable aThrowable)
    {
        this.printCat();
        aThrowable.printStackTrace(System.out);
        mErrors++;
        mTotalErrors++;
    }

    private void printOverrideSequence()
    {
        mWriter.write(this.ESC + "4A");
    }

    private void printCatFace()
    {
        if (this.auditFinished && mTotalErrors > 0) {
            mWriter.println("( o .o)");
        } else {
            mWriter.println("( ^ .^)");
        }
    }

    private void printCat()
    {
        this.printOverrideSequence();
        if (this.catCalls %2 == 0) {
            mWriter.println("-_-_-_-_,------,");
            mWriter.println("-_-_-_-_|   /\\_/\\");
            mWriter.print("-_-_-_-^|__");
            this.printCatFace();
            mWriter.println("-_-_-_-  \"\"  \"\"");
        } else {
            mWriter.println("_-_-_-_-,------,");
            mWriter.println("_-_-_-_-|   /\\_/\\");
            mWriter.print("_-_-_-_^|__");
            this.printCatFace();
            mWriter.println("_-_-_-_  \"\"  \"\"");
        }
        mWriter.flush();
        this.catCalls++;
        
        try {
            Thread.sleep(100);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}