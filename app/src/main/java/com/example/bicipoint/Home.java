package com.example.bicipoint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    public BottomNavigationView bottomNavigation;
    public Fragment fragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ToolBar
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
            fragment = new FragmentHome();

            switch (item.getItemId()) {

                case R.id.mn_mapa: {
                    fragment = new FragmentMap();
                    showFragment();
                    return true;
                }
                case R.id.mn_record: {
                    fragment = new FragmentRecord();
                    showFragment();
                    return true;
                }
                case R.id.mn_inicio:
                    fragment = new FragmentHome();
                    showFragment();
                    return true;
                case R.id.mn_perfil:
                    fragment = new FragmentProfile();
                    showFragment();
                    return true;
                case R.id.mn_premios:
                    fragment = new FragmentPrize();
                    showFragment();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        });
    }

    private void showFragment() {
        Fragment fr = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_home, fr)
                .setReorderingAllowed(true).addToBackStack(null)
                .commit();
    }



    /*
package com.example.fragments

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : Activity() {

    private var fragTag: String? = ""
    private var mCurrentFrag: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            fragTag = savedInstanceState.getString("frag_tag")
            //            mCurrentFrag = getFragmentManager().getFragment(savedInstanceState, fragTag);
            mCurrentFrag = fragmentManager.findFragmentByTag(fragTag)
        }

        if (mCurrentFrag == null) {
            mCurrentFrag = FragmentMain()
            fragTag = "fragment_main"
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_place, mCurrentFrag, fragTag)
                    .addToBackStack(null)
                    .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        var fr: Fragment? = null
        when (item.itemId) {
            R.id.action_main -> {
                fr = FragmentMain()
                fragTag = "fragment_main"
            }
            R.id.action_a -> {
                fr = FragmentA()
                fragTag = "fragment_a"
            }
            R.id.action_b -> {
                fr = FragmentB()
                fragTag = "fragment_b"
            }
            R.id.action_c -> {
                fr = FragmentC()
                fragTag = "fragment_c"
            }
            else -> returnVal = false
        }

        if (returnVal) {
            mCurrentFrag = fr
            val fm = fragmentManager
            val fragmentTransaction = fm.beginTransaction()
            fragmentTransaction
                    .replace(R.id.fragment_place, fr, fragTag)
                    .addToBackStack(null)
                    .commit()
            return returnVal
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(this.localClassName, " onSaveInstanceState.")
        outState.putString("frag_tag", fragTag)
        //        getFragmentManager().putFragment(outState, fragTag, mCurrentFrag);
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(this.localClassName, " onDestroy.")
    }
}



*/
}