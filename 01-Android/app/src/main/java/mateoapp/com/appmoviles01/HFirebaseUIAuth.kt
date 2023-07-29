package mateoapp.com.appmoviles01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class HFirebaseUIAuth : AppCompatActivity() {

    //Callback del INTENT de LOGIN
    private val respuestaLoginAuthUi = registerForActivityResult(FirebaseAuthUIActivityResultContract()){
        res: FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode == RESULT_OK){
            if(res.idpResponse != null){
                //Logica de negocio
                seLogeo(res.idpResponse!!)
            }
        }
    }

    fun seLogeo(res: IdpResponse){
        val btnLogin: Button = findViewById(R.id.btn_login_firebase)
        val btnLogout: Button = findViewById(R.id.btn_logout_firebase)
        btnLogout.visibility = View.VISIBLE
        btnLogin.visibility = View.INVISIBLE
        if(res.isNewUser == true){
            registrarUsuariosPorPrimeraVez(res)
        }
    }

    fun registrarUsuariosPorPrimeraVez()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)
    }
}