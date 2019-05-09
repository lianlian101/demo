package com.mvn.test.util.customcache;

/**
 * 
 * 创建时间: 2019年5月7日 创建人 zhb 说明: 缓存实体类
 *
 */
public class EntityCache {
    
    /**
     * 保存的数据
     */
    private Object datas;
   
    /**
     * 设置数据失效时间，单位毫秒；-1(默认):永不失效、-2:不存在
     */
    private long timeOut = -1L;
   
    /**
     * 数据保存时间，默认为当前时间
     */
    private long createTime = System.currentTimeMillis();

    public EntityCache() {
        super();
    }

    public EntityCache(Object datas, long timeOut, long createTime) {
        super();
        this.datas = datas;
        this.timeOut = timeOut;
        this.createTime = createTime;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "EntityCache [datas=" + datas + ", timeOut=" + timeOut + ", createTime=" + createTime + "]";
    }

    
}
