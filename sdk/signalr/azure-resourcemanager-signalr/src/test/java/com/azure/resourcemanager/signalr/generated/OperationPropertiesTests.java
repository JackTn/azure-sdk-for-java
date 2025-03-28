// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.signalr.generated;

import com.azure.core.util.BinaryData;
import com.azure.resourcemanager.signalr.models.Dimension;
import com.azure.resourcemanager.signalr.models.LogSpecification;
import com.azure.resourcemanager.signalr.models.MetricSpecification;
import com.azure.resourcemanager.signalr.models.OperationProperties;
import com.azure.resourcemanager.signalr.models.ServiceSpecification;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

public final class OperationPropertiesTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        OperationProperties model = BinaryData.fromString(
            "{\"serviceSpecification\":{\"metricSpecifications\":[{\"name\":\"dosyg\",\"displayName\":\"paojakhmsbzjh\",\"displayDescription\":\"zevdphlx\",\"unit\":\"lthqtrgqjbp\",\"aggregationType\":\"fsinzgvfcjrwzoxx\",\"fillGapWithZero\":\"felluwfzitonpe\",\"category\":\"pjkjlxofpdv\",\"dimensions\":[{\"name\":\"xypininmayhuybbk\",\"displayName\":\"depoog\",\"internalName\":\"uvamiheognarxzxt\",\"toBeExportedForShoebox\":false},{\"name\":\"usivye\",\"displayName\":\"ciqihnhung\",\"internalName\":\"jzrnf\",\"toBeExportedForShoebox\":true},{\"name\":\"ispe\",\"displayName\":\"tzfkufubl\",\"internalName\":\"fxqeof\",\"toBeExportedForShoebox\":false},{\"name\":\"jhqjbasvmsmjqul\",\"displayName\":\"sntnbybkzgcw\",\"internalName\":\"clxxwrljdo\",\"toBeExportedForShoebox\":true}]},{\"name\":\"qvkoc\",\"displayName\":\"jdkwtnhxbnjb\",\"displayDescription\":\"sqrglssainq\",\"unit\":\"wnzlljfmppeeb\",\"aggregationType\":\"gxsabkyq\",\"fillGapWithZero\":\"ujitcjcz\",\"category\":\"evndh\",\"dimensions\":[{\"name\":\"d\",\"displayName\":\"p\",\"internalName\":\"bdkvwrwjf\",\"toBeExportedForShoebox\":false},{\"name\":\"hutje\",\"displayName\":\"mrldhu\",\"internalName\":\"zzd\",\"toBeExportedForShoebox\":true},{\"name\":\"hocdgeab\",\"displayName\":\"phut\",\"internalName\":\"ndv\",\"toBeExportedForShoebox\":true}]},{\"name\":\"wyiftyhxhur\",\"displayName\":\"ftyxolniw\",\"displayDescription\":\"cukjf\",\"unit\":\"iawxklry\",\"aggregationType\":\"wckbasyypnd\",\"fillGapWithZero\":\"sgcbac\",\"category\":\"ejk\",\"dimensions\":[{\"name\":\"qgoulznd\",\"displayName\":\"kwy\",\"internalName\":\"gfgibm\",\"toBeExportedForShoebox\":false},{\"name\":\"keqsrxybzqqedq\",\"displayName\":\"bciqfouflm\",\"internalName\":\"kzsmodm\",\"toBeExportedForShoebox\":false},{\"name\":\"gpbkwtmut\",\"displayName\":\"qktapspwgcuert\",\"internalName\":\"kdosvqw\",\"toBeExportedForShoebox\":true},{\"name\":\"gbbjfddgmbmbe\",\"displayName\":\"pbhtqqrolfpfpsa\",\"internalName\":\"bquxigjy\",\"toBeExportedForShoebox\":true}]}],\"logSpecifications\":[{\"name\":\"yfhrtxilnerkujy\",\"displayName\":\"l\"},{\"name\":\"uvfqawrlyxwj\",\"displayName\":\"prbnwbxgjvtbv\"},{\"name\":\"sszdnru\",\"displayName\":\"guhmuouqfpr\"},{\"name\":\"wbnguitnwui\",\"displayName\":\"a\"}]}}")
            .toObject(OperationProperties.class);
        Assertions.assertEquals("dosyg", model.serviceSpecification().metricSpecifications().get(0).name());
        Assertions.assertEquals("paojakhmsbzjh",
            model.serviceSpecification().metricSpecifications().get(0).displayName());
        Assertions.assertEquals("zevdphlx",
            model.serviceSpecification().metricSpecifications().get(0).displayDescription());
        Assertions.assertEquals("lthqtrgqjbp", model.serviceSpecification().metricSpecifications().get(0).unit());
        Assertions.assertEquals("fsinzgvfcjrwzoxx",
            model.serviceSpecification().metricSpecifications().get(0).aggregationType());
        Assertions.assertEquals("felluwfzitonpe",
            model.serviceSpecification().metricSpecifications().get(0).fillGapWithZero());
        Assertions.assertEquals("pjkjlxofpdv", model.serviceSpecification().metricSpecifications().get(0).category());
        Assertions.assertEquals("xypininmayhuybbk",
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).name());
        Assertions.assertEquals("depoog",
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).displayName());
        Assertions.assertEquals("uvamiheognarxzxt",
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).internalName());
        Assertions.assertEquals(false,
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).toBeExportedForShoebox());
        Assertions.assertEquals("yfhrtxilnerkujy", model.serviceSpecification().logSpecifications().get(0).name());
        Assertions.assertEquals("l", model.serviceSpecification().logSpecifications().get(0).displayName());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        OperationProperties model = new OperationProperties().withServiceSpecification(new ServiceSpecification()
            .withMetricSpecifications(Arrays.asList(
                new MetricSpecification().withName("dosyg")
                    .withDisplayName("paojakhmsbzjh")
                    .withDisplayDescription("zevdphlx")
                    .withUnit("lthqtrgqjbp")
                    .withAggregationType("fsinzgvfcjrwzoxx")
                    .withFillGapWithZero("felluwfzitonpe")
                    .withCategory("pjkjlxofpdv")
                    .withDimensions(Arrays.asList(
                        new Dimension().withName("xypininmayhuybbk")
                            .withDisplayName("depoog")
                            .withInternalName("uvamiheognarxzxt")
                            .withToBeExportedForShoebox(false),
                        new Dimension().withName("usivye")
                            .withDisplayName("ciqihnhung")
                            .withInternalName("jzrnf")
                            .withToBeExportedForShoebox(true),
                        new Dimension().withName("ispe")
                            .withDisplayName("tzfkufubl")
                            .withInternalName("fxqeof")
                            .withToBeExportedForShoebox(false),
                        new Dimension().withName("jhqjbasvmsmjqul")
                            .withDisplayName("sntnbybkzgcw")
                            .withInternalName("clxxwrljdo")
                            .withToBeExportedForShoebox(true))),
                new MetricSpecification().withName("qvkoc")
                    .withDisplayName("jdkwtnhxbnjb")
                    .withDisplayDescription("sqrglssainq")
                    .withUnit("wnzlljfmppeeb")
                    .withAggregationType("gxsabkyq")
                    .withFillGapWithZero("ujitcjcz")
                    .withCategory("evndh")
                    .withDimensions(Arrays.asList(
                        new Dimension().withName("d")
                            .withDisplayName("p")
                            .withInternalName("bdkvwrwjf")
                            .withToBeExportedForShoebox(false),
                        new Dimension().withName("hutje")
                            .withDisplayName("mrldhu")
                            .withInternalName("zzd")
                            .withToBeExportedForShoebox(true),
                        new Dimension().withName("hocdgeab")
                            .withDisplayName("phut")
                            .withInternalName("ndv")
                            .withToBeExportedForShoebox(true))),
                new MetricSpecification().withName("wyiftyhxhur")
                    .withDisplayName("ftyxolniw")
                    .withDisplayDescription("cukjf")
                    .withUnit("iawxklry")
                    .withAggregationType("wckbasyypnd")
                    .withFillGapWithZero("sgcbac")
                    .withCategory("ejk")
                    .withDimensions(Arrays.asList(
                        new Dimension().withName("qgoulznd")
                            .withDisplayName("kwy")
                            .withInternalName("gfgibm")
                            .withToBeExportedForShoebox(false),
                        new Dimension().withName("keqsrxybzqqedq")
                            .withDisplayName("bciqfouflm")
                            .withInternalName("kzsmodm")
                            .withToBeExportedForShoebox(false),
                        new Dimension().withName("gpbkwtmut")
                            .withDisplayName("qktapspwgcuert")
                            .withInternalName("kdosvqw")
                            .withToBeExportedForShoebox(true),
                        new Dimension().withName("gbbjfddgmbmbe")
                            .withDisplayName("pbhtqqrolfpfpsa")
                            .withInternalName("bquxigjy")
                            .withToBeExportedForShoebox(true)))))
            .withLogSpecifications(
                Arrays.asList(new LogSpecification().withName("yfhrtxilnerkujy").withDisplayName("l"),
                    new LogSpecification().withName("uvfqawrlyxwj").withDisplayName("prbnwbxgjvtbv"),
                    new LogSpecification().withName("sszdnru").withDisplayName("guhmuouqfpr"),
                    new LogSpecification().withName("wbnguitnwui").withDisplayName("a"))));
        model = BinaryData.fromObject(model).toObject(OperationProperties.class);
        Assertions.assertEquals("dosyg", model.serviceSpecification().metricSpecifications().get(0).name());
        Assertions.assertEquals("paojakhmsbzjh",
            model.serviceSpecification().metricSpecifications().get(0).displayName());
        Assertions.assertEquals("zevdphlx",
            model.serviceSpecification().metricSpecifications().get(0).displayDescription());
        Assertions.assertEquals("lthqtrgqjbp", model.serviceSpecification().metricSpecifications().get(0).unit());
        Assertions.assertEquals("fsinzgvfcjrwzoxx",
            model.serviceSpecification().metricSpecifications().get(0).aggregationType());
        Assertions.assertEquals("felluwfzitonpe",
            model.serviceSpecification().metricSpecifications().get(0).fillGapWithZero());
        Assertions.assertEquals("pjkjlxofpdv", model.serviceSpecification().metricSpecifications().get(0).category());
        Assertions.assertEquals("xypininmayhuybbk",
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).name());
        Assertions.assertEquals("depoog",
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).displayName());
        Assertions.assertEquals("uvamiheognarxzxt",
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).internalName());
        Assertions.assertEquals(false,
            model.serviceSpecification().metricSpecifications().get(0).dimensions().get(0).toBeExportedForShoebox());
        Assertions.assertEquals("yfhrtxilnerkujy", model.serviceSpecification().logSpecifications().get(0).name());
        Assertions.assertEquals("l", model.serviceSpecification().logSpecifications().get(0).displayName());
    }
}
