package org.jay.frame.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.ParentModel;
import org.jay.frame.jdbc.annotation.Table;
import org.jay.frame.util.ReflectUtil;
import org.jay.frame.util.StringUtil;


public class AnnotationFactory {
	public static JdbcModel getJdbcModel(Class c){
		String tableName = null;
		boolean isModel = false;
		Annotation [] annotations = c.getAnnotations();
		JdbcModel model = new JdbcModel();
		for (int i = 0; i < annotations.length; i++) {
			 Annotation anno = annotations[i];
		     Class type = anno.annotationType();
		     if(type == Table.class){
		    	 Table t = (Table)anno;
		    	 tableName =t.name();
		    	 model.setTableName(tableName);
		    	 model.setUncheck(t.uncheck());
		    	 isModel = true;
		     }else if(type == ParentModel.class){
		    	 isModel = true;
		     }
		}
		if(isModel == false){
			return null;
		}
		model.setC(c);
		List<Field> fields = getSuperClassField(c,null);
		for (int i = 0; i < fields.size(); i++) {
			Field f = fields.get(i);
			Annotation [] fieldAnno = f.getAnnotations();
			for (int j = 0; j < fieldAnno.length; j++) {
				Annotation an = fieldAnno[j];
				if(an.annotationType() == Column.class){
					Column ann = (Column)an;
					JdbcColumn column = new JdbcColumn();
					String columnName = ann.name().toLowerCase();
					column.setColumnName(columnName);
					column.setName(f.getName());
					column.setTemp(ann.temp());
					if(ann.primarykey()){
						model.setPrimaryCol(column);
						column.setPrimary(true);
					}
					column.setField(f);
					String name = f.getName();
					Class type = f.getType();
					column.setType(type);
					column.setUnique(ann.unique());
					column.setInsertable(ann.insertable());
					column.setLength(ann.length());
					column.setUpdatable(ann.updatable());
					column.setNullable(ann.nullable());
					column.setGenerator(ann.generator());
					column.setSetMethod(ReflectUtil.getSetMethod(c, name, type));
					column.setGetMethod(ReflectUtil.getGetMethod(c, name, type));
					model.getColMap().put(columnName,column);
					model.getPropertyMap().put(name, column);
					
				}
			}
		}
		return model;
	}
	/**
	 * 获取所有超类的字段
	 * 
	 * @param list
	 * @param model
	 */
	public static List<Field> getSuperClassField(Class model,List<Field> list) {
		if(list == null){
			list = new ArrayList<Field>();
		}
		if (model == Object.class) {
			return list;
		}
		Field[] fieldList = model.getDeclaredFields();
		for (int i = 0; i < fieldList.length; i++) {
			list.add(fieldList[i]);
		}
		getSuperClassField(model.getSuperclass(),list);
		return list;
	}
}
