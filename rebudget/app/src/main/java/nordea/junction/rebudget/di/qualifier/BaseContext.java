package nordea.junction.rebudget.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Qualifier Dagger2 Dependency Injection.
 *
 * Created by Bel on 24/11/2017.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface BaseContext {
}
