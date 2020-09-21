package org.bitcoindevkit.bdkjni.Types

import android.os.Parcelable
import com.fasterxml.jackson.databind.JsonNode
import kotlinx.android.parcel.Parcelize

enum class Network {
    regtest,
    testnet,
}

@Parcelize
data class WalletConstructor(
    var name: String,
    var network: Network,
    var path: String,
    var descriptor: String,
    var change_descriptor: String?,

    var electrum_url: String,
    var electrum_proxy: String?
) : Parcelable

data class TxOut(
    var script_pubkey: String,
    // FIXME: should be ULong
    var value: Long
)

data class UTXO(
    var outpoint: String,
    var txout: TxOut,
    var is_internal: Boolean
)

// FIXME: all Longs should be unsigned
data class TransactionDetails(
    val transaction: JsonNode?,
    val txid: String,
    val timestamp: Long,
    val received: Long,
    val sent: Long,
    val fees: Long,
    val height: Long? // FIXME: should be UInt
)

data class CreateTxResponse(
    val details: TransactionDetails,
    val psbt: String
)

data class SignResponse(
    val psbt: String,
    val finalized: Boolean
)

data class RawTransaction(
    val transaction: String
)

data class Txid(
    val txid: String
)

data class PublicDescriptorsResponse(
    val external: String,
    val internal: String?
)

data class ExtendedKeys(
    val mnemonic: String,
    val ext_priv_key: String,
    val ext_pub_key: String
)

// FIXME: Those should be decleared as UBytes, but jackson doesn't know how to parse them. so we use Ints that are larger and won't overflow to negative
data class WalletPtr(
    var raw: List<Int>,
    var id: List<Int>
)
