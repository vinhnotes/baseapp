package com.vtu.baseapp.viewmodels;

import androidx.lifecycle.ViewModel;
import android.content.Context;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.annotation.NonNull;
import android.view.View;

import com.vtu.baseapp.common.Callback;

public abstract class BaseViewModel extends ViewModel implements Observable {
    protected Context context;
    private boolean isBusy = false;
    private boolean isDisconnect = false;

    @Bindable
    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
        notifyPropertyChanged(com.vtu.baseapp.BR.busy);
    }

    public BaseViewModel() {

    }

    public BaseViewModel(Context context) {
        this.context = context;
    }

    @BindingAdapter("goneUnless")
    public static void setVisibility(View view, boolean isBusy) {
        view.setVisibility(isBusy?View.VISIBLE:View.GONE);
    }

    public abstract void getData(Callback... callback);


    private transient PropertyChangeRegistry mCallbacks;
    @Override
    public void addOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }

}
