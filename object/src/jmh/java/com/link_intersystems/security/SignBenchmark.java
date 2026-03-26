package com.link_intersystems.security;

import org.openjdk.jmh.annotations.*;

import java.security.*;
import java.util.concurrent.TimeUnit;


/**
 * Some code analysis tools say that making fields final improves performance.
 * This test is made to show the impact of both variants so that you can make
 * a decision.
 */
@Fork(warmups = 1, value = 1)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1)
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SignBenchmark {

    @State(Scope.Benchmark)
    public static class SignState {

        protected byte[] content;
        protected PrivateKey privateKeyBytes;
        protected PublicKey publicKeyBytes;
        protected String keyAlgorithm = "RSA";
        protected String signatureAlgorithm = "SHA256withRSA";

        @Setup(Level.Invocation)
        public void setUp() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
            content = new byte[1024 * 1024];
            new SecureRandom().nextBytes(content);

            KeyPairGenerator kpg = KeyPairGenerator.getInstance(keyAlgorithm);

            KeyPair keyPair = kpg.generateKeyPair();

            privateKeyBytes = keyPair.getPrivate();
            publicKeyBytes = keyPair.getPublic();

        }

        public byte[] sign() throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
            Signature signature = Signature.getInstance(signatureAlgorithm);
            signature.initSign(privateKeyBytes);
            signature.update(content);
            return signature.sign();
        }

    }


    @Benchmark
    public byte[] sign(SignState state) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        return state.sign();
    }

    @State(Scope.Benchmark)
    public static class VerifyState extends SignState {

        private byte[] signBytes;

        @Setup(Level.Invocation)
        public void setUp() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
            super.setUp();

            signBytes = sign();
        }

        public boolean verify() throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
            Signature signature = Signature.getInstance(signatureAlgorithm);
            signature.initVerify(publicKeyBytes);
            signature.update(content);
            return signature.verify(signBytes);
        }
    }


    @Benchmark
    public boolean verify(VerifyState state) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        return state.verify();
    }
}

