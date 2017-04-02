package mvp.view.scores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import manulorenzo.me.kittentrate.R;

public class ScoresActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ScoresActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores_activity);

        ScoresFragment scoresFragment = (ScoresFragment) getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        if (scoresFragment == null) {
            // Create the fragment
            scoresFragment = ScoresFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content_fragment, scoresFragment);
            transaction.commit();
        }
    }
}