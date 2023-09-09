package DAO_Modelos;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;
import org.hibernate.query.Query;
import org.hibernate.service.internal.SessionFactoryServiceRegistryBuilderImpl;

import java.util.*;

import javax.imageio.spi.ServiceRegistry;

	
public class Dao_BD /*implements DAO_CRUD_Generica<Object>*/ {

	private SessionFactory configuracionConexion = null;
	private Session conexionBD = null;
	
	private int abrirConexion(Class C) {
		
		try {
			
			configuracionConexion = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(C.getClass()).buildSessionFactory();
			conexionBD = configuracionConexion.openSession();
			
		}catch(ConfigurationException |  SessionException E) {
			//TODO
			return -1;
		}
		return 1;
	}
	
	
	public int actualizar(Object elemento){
		
		try {
			
			 if(abrirConexion(elemento.getClass())== -1)
				 return -1;
			
			 conexionBD.beginTransaction();
			 conexionBD.update(elemento);
			 conexionBD.getTransaction().commit();
		
		}catch(TransactionException E) {
			 return -2;
		}finally {
			conexionBD.close();//TODO
			configuracionConexion.close();
		}
	     return 1; 
    }
	
	
	public int eliminar(Object elemento){

			try {
				if(abrirConexion(elemento.getClass())== -1)
					return -1;
				
				conexionBD.beginTransaction();
				conexionBD.delete(elemento);
				conexionBD.getTransaction().commit();
			
			}catch(TransactionException E) {
				 return -3;
			}finally {
				conexionBD.close();
				configuracionConexion.close();
			}
		     return 1;  
        }
	
	
	public int agregar(Object elemento){  
		try {
					
			if(abrirConexion(elemento.getClass())== -1)
				return -1;
			
			conexionBD.beginTransaction();
			conexionBD.save(elemento);
			conexionBD.getTransaction().commit();
		
		}catch(TransactionException E) {
			return -4;
		}finally {
			
			try {
				
				conexionBD.close();
				configuracionConexion.close();
				
			} catch (Exception e2) {}
		}
		
	     return 1;    
	}
	
	public Object obtener(int claveElemnto,Class claseTipo){
        
		Object result = null;
		try {
			
			if(abrirConexion(claseTipo.getClass()) == -1)
				return null;
			
			conexionBD.beginTransaction();
			result = conexionBD.get(claseTipo,claveElemnto);
			conexionBD.getTransaction().commit();
		
		}catch(TransactionException E) {
			 return null;
		}finally {
			conexionBD.close();//TODO
			configuracionConexion.close();
		}
	     return result;  
     }
	
	public  List obtenerTodos(Object clase, String filtro){
            
		    List<Object> result;
        	
    		try {
    			
    			if(abrirConexion(clase.getClass()) == -1)
    				return null;
    			
    			conexionBD.beginTransaction();
    			result = conexionBD.createQuery("FROM " + clase.getClass().getSimpleName()+" "+filtro).list();
    			conexionBD.getTransaction().commit();
    		
    		}catch(TransactionException E) {
    			return null;
    		}finally {
    			conexionBD.close();//TODO
    			configuracionConexion.close();
    		}
    	     return result;
        }
	
	//---------------------APunte-------------------
						
	public List<Object[]> consultaNativa(Object clase, String query){
		
		List<Object[]> result;
		
		try {
			
			if(abrirConexion(clase.getClass()) == -1)
				return null;
			
			conexionBD.beginTransaction();
			result = (List<Object[]>)conexionBD.createNativeQuery(query).list();
			conexionBD.getTransaction().commit();
		
		}catch(TransactionException E) {
			return null;
		}finally {
			conexionBD.close();//TODO
			configuracionConexion.close();
		}
	     return result;
		
	}
	//------------------------------------------------
	


	public long countElement(Object clase,String filtro) {
		
		long result;
    	Query<Long> query = null;
    	
		try {
			
			if(abrirConexion(clase.getClass()) == -1)
				return -5;
			
			conexionBD.beginTransaction();
			query = conexionBD.createQuery("select count(*) from " + clase.getClass().getSimpleName()+" "+ filtro);//Count de hibernate retorna siempre un objeto Long
			List<Long> lista =  query.list();
			
			result  = lista.get(0).longValue();
			
			conexionBD.getTransaction().commit();
		
		}catch(TransactionException E) {
			return -5;
		}finally {
			conexionBD.close();//TODO
			configuracionConexion.close();
		}
		return result;
	}
	
}
