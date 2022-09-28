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


    /**Busca todos os usuários do banco de dados.
     * @return List<PessoaDTO> - Retorna uma lista de DTO de todas as pessoas existentes.
     */
    public List<PessoaDTO> getAll() {
        return pessoaRepository.findAll().stream().map(PessoaEntity::toDTO).collect(Collectors.toList());
    }
    
    /**Busca todos os ingressos que o usuário em questão possui.
     * @param id Long - Identificador do usuário.
     * @return List<MeusIngressosDTO> - Retorna uma lista de DTO de todos os ingressos do usuário.
     */
    public List<IngressoDTO> getIngressos(@NotNull String nome) {
        return pessoaRepository.findByNome(nome).orElseThrow().getIngressos().stream().map(IngressoEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona um novo usuário ao banco de dados.
     * @param input PessoaPayloadDTO - Dados de um novo usuário
     * @return PessoaDTO - Dados salvos do usuário
     */
    public PessoaDTO save(@NotNull PessoaPayloadDTO newPessoa) {
        pessoaRepository.save(newPessoa.toEntity());
        return pessoaRepository.findByLogin(newPessoa.getLogin()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")).toDTO();
    }

    /**Atualiza informações dos usuários no banco de dados.
     * @param newPessoa PessoaDTO - Dados de um usuário que será atualizado.
     * @return PessoaDTO - Dados atualizados do cinema.
     */
    public PessoaDTO update(@NotNull PessoaDTO newPessoa) {
        PessoaEntity pessoaEntity = pessoaRepository.findById(newPessoa.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"));
        if(newPessoa.getNome() != null) pessoaEntity.setNome(newPessoa.getNome());
        if(newPessoa.getSobrenome() != null) pessoaEntity.setSobrenome(newPessoa.getSobrenome());
        if(newPessoa.getTelefone() != null) pessoaEntity.setTelefone(newPessoa.getTelefone());
        if(newPessoa.getCpf() != null) pessoaEntity.setCpf(newPessoa.getCpf());
        if(newPessoa.getSaldoCarteira() != null) pessoaEntity.setSaldoCarteira(newPessoa.getSaldoCarteira());
        if(newPessoa.getSenha() != null) pessoaEntity.setSenha(newPessoa.getSenha());
        pessoaRepository.save(pessoaEntity);
        return pessoaEntity.toDTO();
    }

    /**Deleta informações do usuário do banco de dados.
     * @param id Long - Identificador de um cinema existente
     */
    public void delete(@NotNull Long id) {pessoaRepository.deleteById(id);}

    public PessoaDTO getDados(String nome) {
        return pessoaRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")).toDTO();
    }

    public List<CinemaDTO> getCinemas(String nome) {
        return pessoaRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")).getCinemas().stream().map(CinemaEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaEntity user = pessoaRepository.findByLogin(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
