package it.ghiglienodigital.docandroid.silienzia;

import android.media.AudioManager;

/**
 * Created by docandroid on 06/06/2017.
 */

public class AudioManagement {

    private AudioManagement(){};

    /**
     * ritorna vero se il telefono è  silenzioso, falso altrimenti
     * @param audioManager
     * @return
     */
    public static boolean isSilenzioso(AudioManager audioManager){
        return audioManager.getRingerMode()==AudioManager.RINGER_MODE_SILENT;

    }

    /**
     * inverte il silenzioso
     * @param audioManager
     */
    public static void invertAudio(AudioManager audioManager){
        /*
        audioManager.setRingerMode(
                isSilenzioso(audioManager) ?
                AudioManager.RINGER_MODE_NORMAL :
                AudioManager.RINGER_MODE_SILENT
        );
        */
        if(AudioManagement.isSilenzioso(audioManager)){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }else{
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }
    }
}
