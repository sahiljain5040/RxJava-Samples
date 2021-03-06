package com.example.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.rxjavademo.buffer.BufferDemoFragment;
import com.example.rxjavademo.concurrency.ConcurrencyDemoFragment;
import com.example.rxjavademo.debounce.DebounceDemoFragment;
import com.example.rxjavademo.polling.PollingDemoFragment;
import com.example.rxjavademo.rx_bus.RxbusMainFragment;
import com.example.rxjavademo.twoway_data_binding.PublishSubjectDemoFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.concurrency)
    public void concurrencyDemo(){
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, ConcurrencyDemoFragment.newInstance(), ConcurrencyDemoFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.buffer)
    public void bufferDemo() {
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, BufferDemoFragment.newInstance(), BufferDemoFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.debounce)
    public void debounceDemo(){
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, DebounceDemoFragment.newInstance(), DebounceDemoFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.publish_subject)
    public void publishSubject(){
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, PublishSubjectDemoFragment.newInstance(), PublishSubjectDemoFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.polling)
    public void pollingDemo(){
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, PollingDemoFragment.newInstance(), PollingDemoFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.rx_bus)
    public void rxBusDemo(){
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, RxbusMainFragment.newInstance(), PollingDemoFragment.TAG)
                .addToBackStack(null)
                .commit();
    }






















    private void textRxJava(){

        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello Sahil");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, s + " Recieved!!!", Toast.LENGTH_SHORT).show();
            }
        };

        myObservable.subscribe(mySubscriber);
    }

    private void simpleRx(){
        Observable<String> myObservable = Observable.just("Hello World1", "Hello World2");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this, s + " Recieved!!!", Toast.LENGTH_SHORT).show();
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Toast.makeText(MainActivity.this,"onErrorAction Recieved!!!", Toast.LENGTH_SHORT).show();
            }
        };

        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                Toast.makeText(MainActivity.this,"onCompleteAction Recieved!!!", Toast.LENGTH_SHORT).show();
            }
        };

        myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
    }

    private void rxLambda(){
       /* Observable.just("Hello World")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(MainActivity.this, s + " Recieved!!!", Toast.LENGTH_SHORT).show();
                    }
                });*/

        Observable.just("Hello World")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> Toast.makeText(MainActivity.this, s + " Recieved!!!", Toast.LENGTH_SHORT).show());
    }
}
