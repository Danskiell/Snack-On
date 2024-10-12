package com.example.snack;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.result.Credentials;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;



public class Auth0Manager {
    private Auth0 auth0;
    private Context context;

    public Auth0Manager(Context context){
        this.context = context;

        String clienteId = context.getString(R.string.com_auth0_client_id);
        String domain = context.getString(R.string.com_auth0_domain);

        this.auth0 = new Auth0(clienteId, domain);

    }

    public void login(Activity activity){
        WebAuthProvider.login(auth0)
                .withScheme("snack")
                .withScope("openid profile email")
                .start(activity, new Callback<Credentials, AuthenticationException>() {

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Log.e("Auth0", "Falha ao autenticar", exception);
                    }
                    @Override
                    public void onSuccess(Credentials credentials) {
                        // Salvar os tokens de autenticação no SharedPreferences
                        // Utilizar o credentials para fazer chamadas à API do backend
                        Log.d("Auth0", "Autenticado com sucesso:" + credentials.getAccessToken());
                        Intent intent = new Intent(activity, ComprasActivity.class);
                        activity.startActivity(intent);
                    }






                });
    }



}
