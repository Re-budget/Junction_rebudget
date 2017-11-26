package nordea.junction.rebudget.api;

import nordea.junction.rebudget.model.ProfileResult;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 *
 *
 * Created by Bel on 25/11/2017.
 */

public interface RestApi {

    String X_LUNNI_USER = "X-Lunni-User";
    String X_LUNNI_PASS = "X-Lunni-Pass";
    String X_LUNNI_DEVICE = "X-Lunni-Device";

    @GET("v1/get_profile/")
    Observable<ProfileResult> getProfile(@Header(X_LUNNI_USER) String xLunniUser,
                                         @Header(X_LUNNI_PASS) String xLunniPass,
                                         @Header(X_LUNNI_DEVICE) String xLunniDevice);
}
