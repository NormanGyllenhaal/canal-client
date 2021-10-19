package top.javatool.canal.client.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author yang peng
 * @date 2019/3/2915:07
 */
public class EntryUtil {


    private static Map<Class, Map<String, String>> cache = new ConcurrentHashMap<>();


    /**
     * 获取字段名称和实体属性的对应关系
     *
     * @param c class
     * @return map
     */
    public static Map<String, String> getFieldName(Class c) {
        Map<String, String> map = cache.get(c);
        if (map == null) {
            List<Field> fields = distinct(FieldUtils.getAllFieldsList(c));
            //如果实体类中存在column 注解，则使用column注解的名称为字段名
            map = fields.stream().filter(EntryUtil::notTransient)
                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                    .collect(Collectors.toMap(EntryUtil::getColumnName, Field::getName));
            cache.putIfAbsent(c, map);
        }
        return map;
    }
    
    /**
     * 去重
     * @param fields  fields
     * @return fields
     */
    private static List<Field> distinct(List<Field> fields){
        return fields.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(EntryUtil::getColumnName))),
                                ArrayList::new
                        )
                );
    }

    private static String getColumnName(Field field) {
        Column annotation = field.getAnnotation(Column.class);
        if (Objects.isNull(annotation) || StringUtils.isEmpty(annotation.name())) {
            return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
        }
        return annotation.name();
    }

    private static boolean notTransient(Field field) {
        Transient annotation = field.getAnnotation(Transient.class);
        return annotation == null;
    }


}
