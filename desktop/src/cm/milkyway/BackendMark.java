package cm.milkyway;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//When a class imports backend classes, add this to mark
@Retention(RetentionPolicy.SOURCE)
public @interface BackendMark
{
}
