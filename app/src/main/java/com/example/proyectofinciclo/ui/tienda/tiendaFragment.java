
public class tiendaFragment extends Fragment {

    private PerfilViewModel mViewModel;

    public static tiendaFragment newInstance() {
        return new tiendaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tiendaFragment, container, false);
        WebView myWebView = (WebView) findViewById(R.id.webviewshop);
        myWebView.loadUrl("http://www.example.com");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

}