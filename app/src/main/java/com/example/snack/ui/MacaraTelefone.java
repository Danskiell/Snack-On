package com.example.snack.ui;
import android.annotation.SuppressLint;
import android.text.Editable;
import android.widget.EditText;
import android.text.TextWatcher;

public class MacaraTelefone implements TextWatcher {
    private static final String PHONE_NUMBER_MASK = "(##) #####-####";
    private final EditText editText;

    @SuppressLint("EditText")
    public MacaraTelefone(EditText editText){
        this.editText = editText;
    }

    private String unmask(String s) {
        return s.replaceAll("[^\\d]", "");
    }

    private String aplicarMascara(String s) {
        StringBuilder masked = new StringBuilder();
        int index = 0;
        for (char m : PHONE_NUMBER_MASK.toCharArray()) {
            if (m != '#') {
                masked.append(m);
                continue;
            }
            try {
                masked.append(s.charAt(index));
            } catch (Exception e) {
                break;
            }
            index++;
        }
        return masked.toString();
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        editText.removeTextChangedListener(this);
        String cleanString = unmask(s.toString());
        aplicarMascara(cleanString);
        editText.setSelection(editText.getText().length()); // Manter o cursor no final do texto
        editText.addTextChangedListener(this);
    }





}
