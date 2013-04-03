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
    private int currentTailLength = 8;
    private final int maxTailLength = 50;
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
        if (this.catCalls > 0) {
            mWriter.write(this.ESC + "4A");
        }
    }

    private void printCatFace()
    {
        if (this.auditFinished && mTotalErrors > 0) {
            mWriter.println("( o .o)  ");
        } else {
            mWriter.println("( ^ .^)  ");
        }
    }

    private void printTail(Integer tailLength)
    {
        int elementsWritten = 1;
        String firstChar;
        String secondChar;
        String tail = "";

        if (this.catCalls % 2 == 0) {
            if (tailLength % 2 == 0) {
                firstChar = "-";
                secondChar = "_";
            } else {
                firstChar = "_";
                secondChar = "-";
            }
        } else {
            if (tailLength % 2 == 0) {
                firstChar = "_";
                secondChar = "-";
            } else {
                firstChar = "-";
                secondChar = "_";
            }
        }

        while (elementsWritten <= tailLength) {
            elementsWritten++;
            if (elementsWritten % 2 == 0) {
                tail = tail + secondChar;
            } else {
                tail = tail + firstChar;
            }
        }

        mWriter.print(tail);
    }

    private void lengthenTail()
    {
        // Lengthen tail by 2 if tail hasn't reached maximum length
        if (this.currentTailLength < this.maxTailLength) {
            this.currentTailLength += 4;
        }
    }

    private void printCat()
    {
        this.printOverrideSequence();
        if (this.catCalls %2 == 0) {
            this.printTail(this.currentTailLength);
            mWriter.println(",------,");
            this.printTail(this.currentTailLength);
            mWriter.println("|   /\\_/\\  ");
            this.printTail(this.currentTailLength - 1);
            mWriter.print("^|__");
            this.printCatFace();
            this.printTail(this.currentTailLength - 1);
            mWriter.println("  \"\"  \"\"");
        } else {
            this.printTail(this.currentTailLength);
            mWriter.println(",------,");
            this.printTail(this.currentTailLength);
            mWriter.println("|  /\\_/\\  ");
            this.printTail(this.currentTailLength - 1);
            mWriter.print("~|_");
            this.printCatFace();
            this.printTail(this.currentTailLength - 1);
            mWriter.println(" \"\"  \"\"  ");
        }
        mWriter.flush();
        this.catCalls++;
        this.lengthenTail();
        
        try {
            Thread.sleep(100);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}