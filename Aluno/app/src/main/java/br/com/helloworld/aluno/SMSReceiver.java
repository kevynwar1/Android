package br.com.helloworld.aluno;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by kevyn on 13/04/2017.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //retornara as mensagens sms enviadas  na forma de object
        Object[] mensagens = (Object[]) intent.getExtras().get("pdus");
// mensagem é tratada internamente como um byte
        byte[] primeira = (byte[]) mensagens[0];

//converter o array de bytes

        SmsMessage sms = SmsMessage.createFromPdu(primeira);
        //descobrindo qual telefone enviou o sms
        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDAO dao = new AlunoDAO(context);
        boolean isaluno = dao.isaluno(telefone);

        dao.close();

        if (isaluno) {
            MediaPlayer media = MediaPlayer.create(context, R.raw.ignite);
            media.start();
            Toast.makeText(context, "tocando musica>>>", Toast.LENGTH_SHORT).show();
        }


    }
}
