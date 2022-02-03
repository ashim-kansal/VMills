package com.vmotors;

public abstract class BasePresenter<V extends BaseView> {

    protected V view;

    protected void onBind() {

    }

    protected void onUnbind() {

    }

    public final void bind(V viewToBind) {
        view = viewToBind;
        onBind();
     }

    public final void unbind() {
        onUnbind();
        view = null;
    }

}
