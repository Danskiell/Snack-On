package com.example.snack;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GerenciarProdutoActivity extends AppCompatActivity {

    private ApiService apiService;
    private Uri imageUri;

    private Button botaoFoto, botaoEnviar;
    private EditText inputNomeProduto, inputDescProduto, inputPrecoProduto, inputQuantidadeProduto;
    private Spinner spinnerCategoria;
    private ImageView cameraIcon;

    // Gerenciador de resultados para abrir a galeria
    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    cameraIcon.setImageURI(imageUri); // Atualiza a imagem exibida
                    Log.d("Imagem", "Imagem selecionada: " + imageUri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_produto);

        inputNomeProduto = findViewById(R.id.inputNomeProduto);
        inputDescProduto = findViewById(R.id.inputDescProduto);
        inputPrecoProduto = findViewById(R.id.inputPrecoProduto);
        inputQuantidadeProduto = findViewById(R.id.inputQntProduto);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        cameraIcon = findViewById(R.id.camera_icon);

        botaoFoto = findViewById(R.id.botaofoto);
        botaoEnviar = findViewById(R.id.botaoEnviar);

        String[] categories = {"Salgados", "Doces", "Bebidas", "Lanches", "Bolos", "Outros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategoria.setAdapter(adapter);

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        botaoFoto.setOnClickListener(view -> openGallery());
        botaoEnviar.setOnClickListener(view -> cadastrarProduto());
    }

    private void openGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            resultLauncher.launch(intent);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissão concedida", Toast.LENGTH_SHORT).show();
                openGallery();
            } else {
                Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cadastrarProduto() {
        String nome = inputNomeProduto.getText().toString();
        String preco = inputPrecoProduto.getText().toString();
        String quantidade = inputQuantidadeProduto.getText().toString();
        String descricao = inputDescProduto.getText().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();

        if (imageUri == null) {
            Toast.makeText(this, "Por favor, selecione uma imagem", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody nomePart = RequestBody.create(MediaType.parse("text/plain"), nome);
        RequestBody precoPart = RequestBody.create(MediaType.parse("text/plain"), preco);
        RequestBody quantidadePart = RequestBody.create(MediaType.parse("text/plain"), quantidade);
        RequestBody descricaoPart = RequestBody.create(MediaType.parse("text/plain"), descricao);
        RequestBody categoriaPart = RequestBody.create(MediaType.parse("text/plain"), categoria);

        try {
            ContentResolver contentResolver = getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(imageUri);

            File tempFile = new File(getCacheDir(), "temp_image.jpg");
            try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            }

            RequestBody requestFile = RequestBody.create(MediaType.parse(contentResolver.getType(imageUri)), tempFile);
            MultipartBody.Part imagemPart = MultipartBody.Part.createFormData("imagem", tempFile.getName(), requestFile);

            Call<Void> call = apiService.cadastrarProduto(nomePart, precoPart, quantidadePart , descricaoPart, categoriaPart, imagemPart);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(GerenciarProdutoActivity.this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GerenciarProdutoActivity.this, "Erro ao cadastrar produto", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(GerenciarProdutoActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("GerenciarProduto", "Erro ao processar a imagem: " + e.getMessage(), e);
            Toast.makeText(this, "Erro ao processar a imagem", Toast.LENGTH_SHORT).show();
        }
    }
}
