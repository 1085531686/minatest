package learn.test.dao;

import java.io.InputStream;

import learn.test.dao.dto.Enwaste;
import learn.test.dao.mapper.EnwasteMapper;
import learn.test.dao.mapper.ExwasteMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class DaoContext {

	private static SqlSessionFactory  sessionFactory = null;
	static String resource = "configuration.xml";
	public static void  init(){
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
		    sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession (){
		return sessionFactory.openSession();
	}
	
	public static EnwasteMapper getEnwasteMapper(){
		SqlSession session = DaoContext.getSession();
		EnwasteMapper enmapper = session.getMapper(EnwasteMapper.class);
		return enmapper;
	}
	
	public static ExwasteMapper getExwasteMapper(){
		SqlSession session = DaoContext.getSession();
		ExwasteMapper exmapper = session.getMapper(ExwasteMapper.class);
		return exmapper;
	}
	

	public  void test() {
		DaoContext.init();
		SqlSession session = DaoContext.getSession();
		EnwasteMapper enmapper = session.getMapper(EnwasteMapper.class);
		Enwaste enwaste = new Enwaste();
		enwaste.setWASTEID("0571220160226150325000");
		enwaste.setMODIFYFLAG(new Short((short)0));
		enwaste =enmapper.selectByPrimaryKey(enwaste);
	
		System.out.println(enwaste.getIMAGEFILENAME());
	}
	

	public void Test2(){
		DaoContext.init();
		
		Enwaste en =new Enwaste();
		en.setEntimeStr("2000-01-01 00:00:00");
		en.setENSTATION("002");
		en.setENLANE("00");
		en.setVLP("´¨11ssdd");
		EnwasteMapper enmapper=getEnwasteMapper();
		en =enmapper.selectByEnInfo(en);
		System.out.println(en);
	}
}
