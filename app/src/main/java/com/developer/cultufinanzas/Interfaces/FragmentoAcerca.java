package com.developer.cultufinanzas.Interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.developer.cultufinanzas.R;

/**
 * Created by Gerza on 25/04/2016.
 */
public class FragmentoAcerca extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.scroll, container, false);



        return view;
    }

}
