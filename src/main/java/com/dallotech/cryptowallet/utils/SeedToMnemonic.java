package com.dallotech.cryptowallet.utils;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;

import java.util.Objects;

public class SeedToMnemonic {
    public static String getMnemonicOfSeed() {
        NetworkParameters params = TestNet3Params.get();
        Wallet wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH);

        DeterministicSeed deterministicSeed = wallet.getKeyChainSeed();
        System.out.println("seed: " + deterministicSeed.toString());

        System.out.println("creation time: " + deterministicSeed.getCreationTimeSeconds());
        String mnemonic = Utils.SPACE_JOINER.join(Objects.requireNonNull(deterministicSeed.getMnemonicCode()));
        System.out.println("mnemonicCode: " + mnemonic);
        return mnemonic;

    }

    public static Wallet getWalletFromSeed(NetworkParameters networkParameters, DeterministicSeed deterministicSeed){
        return Wallet.fromSeed(networkParameters,deterministicSeed);
    }
}
