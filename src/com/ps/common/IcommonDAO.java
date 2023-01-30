package com.ps.common;

import java.util.List;

public interface IcommonDAO<T> {
    
    public int create(T t);
    public List<T> edit(T t);

     public List<T> getAll();
     
     public int updateVal(T t);
    
   
    
    
    
    
    
    
//    // DepartmentModel
//    public int addDept(T t);
//    
//    
//    //rank Model
//    public int addRank(T t);
   
    
    
}
