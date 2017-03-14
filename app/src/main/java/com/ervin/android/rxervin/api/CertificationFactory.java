package com.ervin.android.rxervin.api;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Ervin on 2017/3/14.
 */

public class CertificationFactory {

    public static SSLContext getSLLContext(Context context){
        SSLContext sslContext = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream cerInputStream = context.getAssets().open("srca.cer");
            Certificate ca = cf.generateCertificate(cerInputStream);

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
            TrustManager[] trustManagers = tmf.getTrustManagers();

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  sslContext;
    }
}
