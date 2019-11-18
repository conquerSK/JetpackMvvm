package com.shenkai.core.base;


public interface IBaseView {
    /**
     * 初始化数据
     */
    void initData();
    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();
}
