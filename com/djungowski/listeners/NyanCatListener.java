package com.djungowski.listeners;

import java.io.PrintWriter;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.AutomaticBean;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;

/**
 * Listener for checkstyle that prints Nyan Cat.
 *
 * @author Dominik Jungowski
 *
 */
public class NyanCatListener
    extends AutomaticBean
    implements AuditListener
{
    /**
     * Writer to write to System.out.
     */
    private PrintWriter mWriter = new PrintWriter(System.out);

    /**
     * Boolean to indicate whether the audit is finished.
     */
    private boolean auditFinished = false;

    /**
     * How many errors were found.
     */
    private int mTotalErrors;

    /**
     * The ESC key as Unicode.
     */
    private final String escape = "\u001b[";

    /**
     * Current length of the tail.
     */
    private int currentTailLength = 8;

    /**
     * Maximum length of the tail.
     */
    private final int maxTailLength = 50;

    /**
     * How many times the cat has been printed.
     */
    private int catCalls = 0;

    /**
     * Value by how many chars the tail is lengthened after each step.
     */
    private final int lengthenStepSize = 4;

    /**
     * Dash.
     */
    private final String dash = "-";

    /**
     * Underscore.
     */
    private final String underscore = "_";

    /**
     * Number of milliseconds to sleep after each Nyan Cat output.
     */
    private final int sleepTime = 100;

    /**
     * When audit starts: print Nyan Cat.
     *
     * @param aEvt The audit event that occurs during the style check
     */
    public void auditStarted(final AuditEvent aEvt)
    {
        mTotalErrors = 0;
        this.printCat();
    }

    /**
     * When audit finishes: print Nyan Cat.
     *
     * @param aEvt The audit event that occurs during the style check
     */
    public void auditFinished(final AuditEvent aEvt)
    {
        this.auditFinished = true;
        this.printCat();
    }

    /**
     * When a file starts: print Nyan Cat.
     *
     * @param aEvt The audit event that occurs during the style check
     */
    public void fileStarted(final AuditEvent aEvt)
    {
        this.printCat();
    }

    /**
     * When a file finishes: print Nyan Cat.
     *
     * @param aEvt The audit event that occurs during the style check
     */
    public void fileFinished(final AuditEvent aEvt)
    {
        this.printCat();
    }

    /**
     * When an error occurs: print Nyan Cat.
     *
     * @param aEvt The audit event that occurs during the style check
     */
    public void addError(final AuditEvent aEvt)
    {
        this.printCat();
        if (SeverityLevel.ERROR.equals(aEvt.getSeverityLevel())) {
            mTotalErrors++;
        }
    }

    /**
     * When en Exception is thrown: print Nyan Cat.
     *
     * @param aEvt The audit event that occurs during the style check
     * @param aThrowable a throwable object
     */
    public void addException(final AuditEvent aEvt, final Throwable aThrowable)
    {
        this.printCat();
        aThrowable.printStackTrace(System.out);
        mTotalErrors++;
    }

    /**
     * Write to console a command that enables overwriting the Nyan Cat.
     */
    private void printOverwriteSequence()
    {
        if (this.catCalls > 0) {
            mWriter.write(this.escape + "4A");
        }
    }

    /**
     * Print the Cat Face depending on the Status of the Audit.
     */
    private void printCatFace()
    {
        // If the Audit is finished and there were errors, Nyan Cat is not amused
        if (this.auditFinished && mTotalErrors > 0) {
            mWriter.println("( o .o)  ");
        } else {
            mWriter.println("( ^ .^)  ");
        }
    }

    /**
     * Print the tail.
     *
     * @param tailLength    How long the tail should be
     */
    private void printTail(final Integer tailLength)
    {
        int elementsWritten = 1;
        String firstChar;
        String secondChar;
        String tail = "";

        if (this.catCalls % 2 == 0) {
            if (tailLength % 2 == 0) {
                firstChar = this.dash;
                secondChar = this.underscore;
            } else {
                firstChar = this.underscore;
                secondChar = this.dash;
            }
        } else {
            if (tailLength % 2 == 0) {
                firstChar = this.underscore;
                secondChar = this.dash;
            } else {
                firstChar = this.dash;
                secondChar = this.underscore;
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

    /**
     * Lengthen the tail if it hasn't reached maximum length.
     */
    private void lengthenTail()
    {
        // Lengthen tail by 2 if tail hasn't reached maximum length
        if (this.currentTailLength < this.maxTailLength) {
            this.currentTailLength += this.lengthenStepSize;
        }
    }

    /**
     * Print the Nyan Cat.
     * _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-,------,
     * _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-|   /\_/\
     * -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-^|__( o .o)
     * -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-  ""  ""
     *
     */
    private void printCat()
    {
        this.printOverwriteSequence();
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
            Thread.sleep(this.sleepTime);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}