package entity;

import java.util.List;

public class PagerBean<T>
{
  private int page;
  private int rows;
  private int total;
  private List<T> data;
  
  public int getPage()
  {
    return this.page;
  }
  
  public void setPage(int page)
  {
    this.page = page;
  }
  
  public int getRows()
  {
    return this.rows;
  }
  
  public void setRows(int rows)
  {
    this.rows = rows;
  }
  
  public List<T> getData()
  {
    return this.data;
  }
  
  public void setData(List<T> data)
  {
    this.data = data;
  }
  
  public int getTotal()
  {
    return this.total;
  }
  
  public void setTotal(int total)
  {
    this.total = total;
  }
  
  public int getOffset()
  {
    return (this.page - 1) * this.rows;
  }
  
  public int getPageCount()
  {
    return this.total % this.rows == 0 ? this.total / this.rows : this.total / this.rows + 1;
  }
}
