package com.example.project.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<Boolean> lockscreenEnabled;

    public SettingsViewModel() {
        // Inisialisasi default setting
        lockscreenEnabled = new MutableLiveData<>(false); // Default: Lockscreen disabled
    }

    /**
     * Getter untuk LiveData lockscreenEnabled.
     * Digunakan untuk memantau perubahan status kunci layar.
     *
     * @return LiveData<Boolean> untuk status lockscreen.
     */
    public LiveData<Boolean> isLockscreenEnabled() {
        return lockscreenEnabled;
    }

    /**
     * Setter untuk mengubah status lockscreenEnabled.
     *
     * @param isEnabled Status baru untuk lockscreen (true jika aktif, false jika tidak).
     */
    public void setLockscreenEnabled(boolean isEnabled) {
        if (lockscreenEnabled.getValue() == null || lockscreenEnabled.getValue() != isEnabled) {
            lockscreenEnabled.setValue(isEnabled);
        }
    }
}