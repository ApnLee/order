package com.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.order.dao.FoodDAO;
import com.order.entity.Food;
import com.order.utils.ComPoolUtil;

public class FoodDAOImpl implements FoodDAO {

	public List<Food> getFoodByStartPage(Food food, int startIndex,
			int maxResult) {
		List<Food> foods=null;
		
		try {
			StringBuilder sb=new StringBuilder("select a.*,b.typeName from food a,foodtype b");
			//按名称查询
			if (food.getFoodName()!=null && !"".equals(food.getFoodName())) {
				 sb.append(" and a.foodName like '%"+food.getFoodName()+"%'");
			}
			//类型查询
			if (food.getTypeName()!=null && !"".equals(food.getTypeName())) {
				sb.append(" and b.typeName like '%"+food.getTypeName()+"%'");					
			}
			
			sb.append(" and a.foodtype_id = b.id");
			sb.append(" limit "+startIndex+","+maxResult+"");
			
			String sql= sb.toString().replaceFirst("and", "where");
			System.out.println("sql:"+sql);
			
			foods=ComPoolUtil.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);			
		}
		return foods;
	}

	public Long getFoodCount(Food food) {
		Long totalCount=0l;
		
		try {
			StringBuilder sb=new StringBuilder("select count(*) from food a,foodtype b");
			//按名称查询
			if (food.getFoodName()!=null && !"".equals(food.getFoodName())) {
				 sb.append(" and a.foodName like '%"+food.getFoodName()+"%'");
			}
			//类型查询
			if (food.getTypeName()!=null && !"".equals(food.getTypeName())) {
				sb.append(" and b.typeName like '"+food.getTypeName()+"'");					
			}
			
			sb.append(" and a.foodtype_id = b.id");
			
			String sql= sb.toString().replaceFirst("and", "where");
			System.out.println("sql_count:"+sql);
			
			totalCount=ComPoolUtil.getQueryRunner().query(sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return totalCount;
	}

	public int addFood(Food food) {
		int count=0;
		try {
			count = ComPoolUtil.getQueryRunner().update(
					"insert into food(foodname,foodType_id,price,mprice,remark,img) values (?,?,?,?,?,?)",
					food.getFoodName(),food.getFoodTypeId(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;

	}

	public Food findFoodById(int id) {
		Food food=null;
		try {
			food=ComPoolUtil.getQueryRunner().query(
					"select f.id,f.foodName,f.price,f.mprice,f.remark,f.img,f.foodType_id as foodTypeId from food f where f.id =?",
					new BeanHandler<Food>(Food.class),
					id);
		} catch (SQLException e) {
			throw new RuntimeException(e);		
		}
		return food;
	}

	public int updateFood(Food food) {
		int count=0;
		
		StringBuilder sb=new StringBuilder("update food set foodName=?, foodType_id=?, price=?, mprice=?, remark=?");
		//图片的路径不为空		
		if(food.getImg()!=null && !"".equals(food.getImg())){
			sb.append(" img = "+food.getImg());
		}
		sb.append(" where id = ?");
		
		try {
			count=ComPoolUtil.getQueryRunner().update(sb.toString(),food.getFoodName(),food.getFoodTypeId(),food.getPrice(),
					food.getMprice(),food.getRemark(),food.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;
	}

	public int deleteFoodById(int id) {
		int count=0;
		try {
			count=ComPoolUtil.getQueryRunner().update("delete from food where id = ?",id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;
	}

}
