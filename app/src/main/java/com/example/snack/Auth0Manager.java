package com.example.snack;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.result.Credentials;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;



public class Auth0Manager {
    private static final String TAG = "Auth0Manager";
    private Auth0 auth0;
    private Context context;

    public Auth0Manager(Context context){
        this.context = context;

        String clienteId = context.getString(R.string.com_auth0_client_id);
        String domain = context.getString(R.string.com_auth0_domain);

        this.auth0 = new Auth0(clienteId, domain);

        Log.d(TAG, "Auth0 iniciado com clienteId: " + clienteId + " e dominio: " + domain);

    }

    public interface Auth0Callback {
        void onSuccess(Credentials credentials);
        void onFailure(String error);
    }

    public void login(Activity activity, Auth0Callback callback){
        Log.d(TAG, "Iniciando processo de login......");

        WebAuthProvider.login(auth0)
                .withScheme("com.example.snack")
                .withScope("openid profile email")
                .withAudience("https://dev-r1uf0w8hoq10d3ef.us.auth0.com/userinfo")
                .start(activity, new Callback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials credentials) {
                        // Salvar os tokens de autenticação no SharedPreferences
                        // Utilizar o credentials para fazer chamadas à API do backend
                        Log.d("Auth0", "Autenticado com sucesso:" + credentials.getAccessToken());
                        callback.onSuccess(credentials);

                        // Ir para a tela de compras
                        Intent intent = new Intent(activity, ComprasActivity.class);
                        activity.startActivity(intent);

                        callback.onSuccess(credentials);
                    }

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Log.e("Auth0", "Falha ao autenticar", exception);

                        if (exception.isBrowserAppNotAvailable()){
                            Toast.makeText(activity, "Navegador nao disponivel", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Falha ao realizar login",
                                    Toast.LENGTH_SHORT).show();
                        }
                        callback.onFailure(exception.getMessage());
                    }




                });
    }



}
