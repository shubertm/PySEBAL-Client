package infbyte.pysebal.listeners;

public interface ImageAnalysisListener {

    void onStart();

    void onNextImage(int number);

    void onFinish();
}
