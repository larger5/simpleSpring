package core.Dao.tableAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // 作用域：属性
@Retention(RetentionPolicy.RUNTIME) // 生命周期：源文件SOURCE、编译CLASS、运行RUNTIME
public @interface Column {

    String value();

}
