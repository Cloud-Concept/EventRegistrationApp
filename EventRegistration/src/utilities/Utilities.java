package utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.salesforce.androidsdk.rest.RestRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilities is factory class that defines a set of methods that perform common, often re-used functions. define most of these common methods under static scope.
 */
public class Utilities {

    public static ProgressDialog _progress;

//    /**
//     * Email pattern
//     */
//
//    private static final String EMAIL_PATTERN =
//            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
//            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * @param activity:container activity that want this dialog to be shown
     *                           Show Loading dialog which is called all over the application
     */
    public static void showloadingDialog(Activity activity) {
        _progress = new ProgressDialog(activity);
        _progress.setMessage("Loading ...");
        _progress.setCancelable(false);
        _progress.show();
    }

    /**
     * check whether progress is showing to user or not
     */
    public static boolean getIsProgressLoading() {
        if (_progress != null) {
            return _progress.isShowing();
        } else {
            return false;
        }
    }

    /**
     * @param activity:container activity that want this diaog to be shown
     * @param text:text          displayed within loading dialog
     *                           Show Loading dialog which is called all over the application.
     */
    public static void showloadingDialog(Activity activity, String text) {
        _progress = new ProgressDialog(activity);
        _progress.setMessage(text);
        _progress.setCancelable(false);
        _progress.show();
    }

    /**
     * dismiss current shown loading dialog
     */
    public static void dismissLoadingDialog() {
        _progress.dismiss();
    }

    /**
     * @param act:container activity that want this message to be shown
     * @param message:the   message to be shown within toast
     *                      Show short message indicating what message want to be displayed to user
     */
    public static void showToast(Activity act, String message) {
        Toast.makeText(act, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param act:container activity that want this message to be shown
     * @param message:the   message to be shown within toast
     *                      Show long message indicating what message want to be displayed to user
     */
    public static void showLongToast(Activity act, String message) {
        Toast.makeText(act, message, Toast.LENGTH_LONG).show();
    }

    public static String getCurrentDate() {
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date resultdate = new Date(yourmilliseconds);
        return sdf.format(resultdate);
    }
}