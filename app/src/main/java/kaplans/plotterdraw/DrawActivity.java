package kaplans.plotterdraw;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class DrawActivity extends AppCompatActivity {
    private CanvasView paintCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_draw);

        //registerForContextMenu((Button)findViewById(R.id.show_menu));

        paintCanvas = (CanvasView) findViewById(R.id.canvas);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Toggle Buttons");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Toggle Buttons") {
            ArrayList<View> allButtons;
            allButtons = ((LinearLayout) findViewById(R.id.canvas)).getTouchables();
            for (View b : allButtons) {
                if (b instanceof Button) {
                    if (b.getVisibility() == View.INVISIBLE) b.setVisibility(View.VISIBLE);
                    else if (b.getVisibility() == View.VISIBLE) b.setVisibility(View.INVISIBLE);
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
    public void backX(View view) {
        paintCanvas.sendAdjustAxis("X");
    }

    public void backY(View view) {
        paintCanvas.sendAdjustAxis("Y");
    }

    public void reset(View view) {
        paintCanvas.sendAdjustAxis("reset");
    }

    public void penAdjustUp(View view) {
        paintCanvas.sendAdjustPen("up");
    }

    public void penAdjustDown(View view) {
        paintCanvas.sendAdjustPen("down");
    }

    public void penUp(View view) {
        paintCanvas.sendGcode("M300 S50.0");
    }

    public void penDown(View view) {
        paintCanvas.sendGcode("M300 S30.0");
    }
    public void stop(View view) {
        paintCanvas.clearGcode();
    }

}
