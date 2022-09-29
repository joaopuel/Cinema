package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.repository.CadeiraRepository;
import com.entra21.grupo1.view.repository.IngressoRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import com.entra21.grupo1.view.repository.SessaoRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService implements UserDetailsService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private IngressoRepository ingressoRepository;
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private CadeiraRepository cadeiraRepository;

    /**Busca os dados de um usuário.
     * @param user Entidade do usuário que está acessando o método.
     * @return DTO com dados do usuário.
     */
    public PessoaDTO getDados(@NotNull PessoaEntity user) {
        return user.toDTO();
    }

    /**Busca todos os ingressos que o usuário possuí.
     * @param user Entidade do usuário que está acessando o método.
     * @return Lista de todos os ingressos do usuário.
     */
    public List<IngressoDTO> getIngressos(@NotNull PessoaEntity user) {
        return pessoaRepository.findByLogin(user.getLogin()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")).getIngressos().stream().map(IngressoEntity::toDTO).collect(Collectors.toList());
    }

    /**Busca todos os cinemas que o respectivo proprietário possuí.
     * @param user Entidade do usuário que está acessando o método.
     * @return Lista de todos os cinemas do proprietário.
     */
    public List<CinemaDTO> getCinemas(@NotNull PessoaEntity user) {
        if(!user.isAdministrador()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas para proprietários de cinemas!");
        }else{
            return pessoaRepository.findByLogin(user.getLogin()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")).getCinemas().stream().map(CinemaEntity::toDTO).collect(Collectors.toList());
        }
    }

    /**Adiciona um novo usuário ao banco de dados.
     * @param newPessoa Dados do novo usuário.
     * @return Dados salvos do novo usuário.
     */
    public PessoaDTO savePessoa(@NotNull PessoaPayloadDTO newPessoa) {
        pessoaRepository.findByLogin(newPessoa.getLogin()).ifPresentOrElse(p ->  {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este login já está em uso");
        }, () -> {
            pessoaRepository.save(newPessoa.toEntity());
        });
        return pessoaRepository.findByLogin(newPessoa.getLogin()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")).toDTO();
    }

    /**Adiciona um novo usuário como prorietário de cinemas ao banco de dados.
     * @param newPessoa Dados do novo usuário.
     * @return Dados salvos do novo proprietário.
     */
    public PessoaDTO saveProprietario(@NotNull PessoaPayloadDTO newPessoa) {
        pessoaRepository.findByLogin(newPessoa.getLogin()).ifPresentOrElse(p ->  {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este login já está em uso");
        }, () -> {
            PessoaEntity pessoaEntity = newPessoa.toEntity();
            pessoaEntity.setAdministrador(true);
            pessoaRepository.save(pessoaEntity);
        });
        return pessoaRepository.findByLogin(newPessoa.getLogin()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")).toDTO();
    }

    /**Atualiza informações do usuário no banco de dados.
     * @param user Entidade do usuário que está acessando o método.
     * @param newPessoa Dados atualizados do usuário.
     * @return Dados atualizados.
     */
    public PessoaDTO update(@NotNull PessoaEntity user, @NotNull PessoaPayloadDTO newPessoa) {
        if(newPessoa.getNome() != null) user.setNome(newPessoa.getNome());
        if(newPessoa.getSobrenome() != null) user.setSobrenome(newPessoa.getSobrenome());
        if(newPessoa.getTelefone() != null) user.setTelefone(newPessoa.getTelefone());
        if(newPessoa.getCpf() != null) user.setCpf(newPessoa.getCpf());
        if(newPessoa.getSenha() != null) user.setSenha(newPessoa.getSenha());
        pessoaRepository.save(user);
        return user.toDTO();
    }

    /**Adiciona valor inserido na carteira do usuário.
     * @param user Entidade do usuário que está acessando o método.
     * @param valor Valor a ser depositado na carteira.
     */
    public void deposito(@NotNull PessoaEntity user, @NotNull Double valor) {
        user.setSaldoCarteira(user.getSaldoCarteira() + valor);
        pessoaRepository.save(user);
    }

    /**Adiciona valor retirado da carteira do usuário.
     * @param user Entidade do usuário que está acessando o método.
     * @param valor Valor a ser retirado da carteira.
     */
    public void retirada(@NotNull PessoaEntity user, @NotNull Double valor) {
        user.setSaldoCarteira(user.getSaldoCarteira() - valor);
        pessoaRepository.save(user);
    }

    /**Deleta informações do usuário do banco de dados.
     * @param user Entidade do usuário que está acessando o método.
     */
    public void delete(@NotNull PessoaEntity user) {pessoaRepository.delete(user);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaEntity user = pessoaRepository.findByLogin(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
