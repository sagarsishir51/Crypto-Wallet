package com.dallotech.cryptowallet.utils;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;

public class RestoreFromMnemonic {
    public static Wallet getWalletFromMnemonic(NetworkParameters networkParameters,String mnemonics,String passphrase, Long creatingTime) throws UnreadableWalletException {

        // Bitcoinj supports hierarchical deterministic wallets (or "HD Wallets"): https://github.com/bitcoin/bips/blob/master/bip-0032.mediawiki
        // HD wallets allow you to restore your wallet simply from a root seed. This seed can be represented using a short mnemonic sentence as described in BIP 39: https://github.com/bitcoin/bips/blob/master/bip-0039.mediawiki

        // Here we restore our wallet from a seed with no passphrase. Also have a look at the BackupToMnemonicSeed.java example that shows how to backup a wallet by creating a mnemonic sentence.
//        String seedCode = "yard impulse luxury drive today throw farm pepper survey wreck glass federal";
//        String passphrase = "";
//        Long creationtime = 1409478661L;

        DeterministicSeed deterministicSeed = new DeterministicSeed(mnemonics, null, passphrase, creatingTime);

        // The wallet class provides a easy fromSeed() function that loads a new wallet from a given seed.
        Wallet wallet = Wallet.fromSeed(networkParameters, deterministicSeed, Script.ScriptType.P2PKH);

        // Because we are importing an existing wallet which might already have transactions we must re-download the blockchain to make the wallet picks up these transactions
        // You can find some information about this in the guides: https://bitcoinj.github.io/working-with-the-wallet#setup
        // To do this we clear the transactions of the wallet and delete a possible existing blockchain file before we download the blockchain again further down.
        System.out.println("seed: " + deterministicSeed.toHexString());

        System.out.println(wallet.toString());
        wallet.clearTransactions(0);
        return wallet;
    }
}
