package PagesAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.manager.BudgetFragment;
import com.example.manager.ExpensesFragment;
import com.example.manager.HomeFragment;
import com.example.manager.ConclusionFragment;

public class PagesAdapter extends FragmentStateAdapter {
    public PagesAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new ExpensesFragment();
        } else if (position == 2) {
            return new BudgetFragment();
        } else if (position == 3) {
            return new ConclusionFragment();
        } else {
            return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}