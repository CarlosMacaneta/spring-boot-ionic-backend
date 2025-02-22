package com.webapplication.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author CarlosMacaneta
 */
@Entity
public class ItemPedido implements Serializable {
    
    @JsonIgnore//nao vai serializar os produtos e pedidos
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();
    
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }
    
    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }
    
    //@JsonIgnore
    public Produto getProduto() {
        return id.getProduto();
    }
    
    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }
    
    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public double getSubTotal() {
        return (preco - desconto) *  quantidade;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(getProduto().getNome());
        sb.append(", Qte: ").append(getQuantidade());
        sb.append(", Preço unitário: ").append(nf.format(getPreco()));
        sb.append(", Subtotal: ").append(nf.format(getSubTotal()));
        sb.append("\n");
        return sb.toString();
    }
    
}
