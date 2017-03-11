package com.developer.cultufinanzas.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.developer.cultufinanzas.R;
import  com.developer.cultufinanzas.utils.Utils;

public class ActividadPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;
    public SearchView mSearchView;
    public MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(ActividadPrincipal.this, PREF_USER_FIRST_TIME, "true"));
        Intent introIntent = new Intent(ActividadPrincipal.this, TutoInicio.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);
        if (isUserFirstTime)
            startActivity(introIntent);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null)
        {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(2));
        }
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
           /* case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                break;*/
            case R.id.item_cuenta:
                fragmentoGenerico = new FragmentoCuenta();
                break;
            case R.id.item_categorias:
                fragmentoGenerico = new FragmentoGlosario();
                break;

            case R.id.item_eventos:
                fragmentoGenerico=new FragmentoEventos();
                break;
            case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
            case R.id.item_acerca:
                fragmentoGenerico=new FragmentoAcerca();
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        searchMenuItem = menu.findItem(R.id.action_carrito);
        mSearchView = (SearchView) searchMenuItem.getActionView();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

           case R.id.action_carrito:
                Intent tutorial=new Intent(getApplicationContext(),TutoInicio.class);
                startActivity(tutorial);
                return  true;


            case R.id.action_salir:
                this.finish();
                System.exit(0);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
