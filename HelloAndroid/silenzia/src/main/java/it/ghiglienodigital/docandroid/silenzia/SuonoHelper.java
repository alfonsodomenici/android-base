package it.ghiglienodigital.docandroid.silenzia;

import android.media.AudioManager;

/**
 * Created by docandroid on 06/06/2017.
 */

public class SuonoHelper {

    public static void onInvertiModalitaSuono( AudioManager audioManager){
        audioManager.setRingerMode(
                isSilenzioso(audioManager)
                        ? AudioManager.RINGER_MODE_NORMAL
                        : AudioManager.RINGER_MODE_SILENT);
    }

    public static boolean isSilenzioso(AudioManager audioManager) {
        return audioManager.getRingerMode()
                == AudioManager.RINGER_MODE_SILENT;
    }
}
