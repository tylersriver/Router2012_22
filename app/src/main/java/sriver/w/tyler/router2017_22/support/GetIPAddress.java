package sriver.w.tyler.router2017_22.support;

import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import sriver.w.tyler.router2017_22.networks.Constants;

/**
 * Created by tyler on 2/6/2017.
 *
 * Class to convert the passed in IP address string to an
 * InetAddress object, which is what we will store in the Adjacency Record.
 */
public class GetIPAddress {

    // -- Fields
    // --------------------------------------------------------------
    InetAddress returnAddress;
    private static GetIPAddress ourInstance = new GetIPAddress();

    // -- Methods
    // --------------------------------------------------------------
    public static GetIPAddress getInstance() {
        return ourInstance;
    }

    private GetIPAddress() {
    }

    /**
     * The goal of this is to provide a generic method to get the IP address of a URL name.
     * This will work with a numeric IP address.
     *
     * You can't use the "getByName("www.oc.edu")" method on the UI thread. So this
     * calls an AsynchTask private class to do that on a new thread.
     *
     * The problem is that the main method goes on before the answer is returned by the remote
     * nameserver, and so the method returns a null pointer.
     *
     * In order to force it to wait I have to use the "get()" method of the AsyncTask.
     *
     * But there is one more thing that needed to be done.  If you use the get() method,
     * it will return the object from the return of the doInBackground method.
     *
     * However, it WON'T do this if you have an onPostExecute() method in the class.
     *
     * So I removed the onPostExecute() from the private class and now get waits for the thread
     * to finish.
     */
    public InetAddress getInetAddress(String hostname) {
        // create the object to manage the thread.  “ThreadedNameServer” is a private class below
        ThreadedNameServer thread = new ThreadedNameServer();
        // spin off the thread. Log a message to that effect.
        thread.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, hostname);
        Log.i(Constants.logTag, " -- Started Thread. Getting ready to wait for results.");
        // use the get method to wait on the thread before proceeding. Also, don't include
        // an "onPostExecute()" method in the private AsyncTask object.
        try {
            returnAddress = thread.get(2000, TimeUnit.MILLISECONDS);
            Log.d(Constants.logTag, " -- Results received.  return address = " + returnAddress.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnAddress;
    }

    /**
     * This is a private threaded task which takes care of posting a request to the local nameserver
     * to resolve the address given.  In the router is should always be a numeric IP address
     *  -- however, if you had a defined name in a local nameserver it would translate that name
     *     to the IP address and return an InetAddress object.
     *
     *   The AsynkTask<…> signature expects a String (IP address), has no progress indicator,
     *       and returns an InetAddress object.
     */
    private class ThreadedNameServer extends AsyncTask<String, Void, InetAddress> {

        /**
         * The doInBackground is where the work is done. All of this is simply
         * to call the getByName off the UI thread!
         */
        @Override
        protected InetAddress doInBackground(String... name) {
            InetAddress address = null;
            try {

                address = InetAddress.getByName(name[0]);
            } catch (UnknownHostException e){
                e.printStackTrace();
            }
            return address;
        }
    }
}
